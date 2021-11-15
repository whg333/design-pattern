package com.whg.client.v07;

import com.whg.api.User;
import com.whg.api.UserService;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.Arrays;

public class ServiceFactory {

    public static UserService getUserService(){
        Object proxy = Proxy.newProxyInstance(
                UserService.class.getClassLoader(),
                new Class[]{UserService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) {
                        beforeInvoke(proxy, method, args);

                        long id = (long) args[0];
                        Object result = null;
                        Exception exception = null;
                        try {
                            result = findUser(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                            exception = e;
                        }

                        afterInvoke(result, exception);
                        return result;
                    }
                });
        return (UserService) proxy;
    }

    private static User findUser(long id) throws Exception{
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
    }

    private static void beforeInvoke(Object proxy, Method method, Object[] args){
        System.out.println();
        System.out.println("执行前");
        System.out.println("执行类="+proxy.getClass().getName());
        System.out.println("执行接口="+ Arrays.toString(proxy.getClass().getInterfaces()));
        System.out.println("执行方法="+method.getName());
        System.out.println("执行参数="+Arrays.toString(args));
        System.out.println();
    }

    private static void afterInvoke(Object result, Exception exception){
        System.out.println();
        System.out.println("执行后");
        System.out.println("执行结果="+result);
        System.out.println("执行异常="+exception);
        System.out.println();
    }

}
