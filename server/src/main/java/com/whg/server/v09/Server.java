package com.whg.server.v09;

import com.whg.api.UserService;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static boolean running = true;
    private static UserService userService = new UserServiceImpl(); //IOC

    public static void main(String[] args) throws Exception {
        int port = 9017;
        ServerSocket server = new ServerSocket(port);
        System.out.println("server start on port="+port);
        while(running){
            Socket client = server.accept();
            System.out.println("accept client:"+client);
            process(client);
            client.close();
        }
        server.close();
    }

    private static void process(Socket client) throws Exception{
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();
        ObjectInputStream oIn = new ObjectInputStream(in);
        ObjectOutputStream oOut = new ObjectOutputStream(out);

        String methodName = oIn.readUTF();
        Class<?>[] paramTypes = (Class<?>[]) oIn.readObject();
        Object[] args = (Object[]) oIn.readObject();
        Method method = userService.getClass().getMethod(methodName, paramTypes);
        Object result = method.invoke(userService, args);

        oOut.writeObject(result);
        oOut.flush();
    }

}
