package com.whg.client.v06;

import com.whg.api.User;
import com.whg.api.UserService;
import com.whg.client.v04.UserServiceImpl;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ServiceFactory {

    public static UserService getUserService(){
        Object proxy = Proxy.newProxyInstance(
                UserService.class.getClassLoader(),
                new Class[]{UserService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) {
                        long id = (long) args[0];
                        return findUser(id);
                    }
                });
        return (UserService) proxy;
    }

    private static User findUser(long id) {
        try {
            Socket client = new Socket("localhost", 9017);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dOut = new DataOutputStream(baos);
            dOut.writeLong(id);

            OutputStream out = client.getOutputStream();
            out.write(baos.toByteArray());
            out.flush();

            InputStream in = client.getInputStream();
            DataInputStream dIn = new DataInputStream(in);
            long uid = dIn.readLong();
            String name = dIn.readUTF();
            int age = dIn.readInt();

            User user = new User(uid, name, age);
            client.close();

            return user;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
