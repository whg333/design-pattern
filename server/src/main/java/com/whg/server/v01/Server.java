package com.whg.server.v01;

import com.whg.api.User;
import com.whg.api.UserService;

import java.io.*;
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
        DataInputStream dIn = new DataInputStream(in);
        DataOutputStream dOut = new DataOutputStream(out);

        long id = dIn.readLong();
        User user = userService.findUser(id);
        dOut.writeLong(user.getId());
        dOut.writeUTF(user.getName());
        dOut.writeInt(user.getAge());
        dOut.flush();
    }

}
