package com.whg.client;

import com.whg.api.User;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception{
        Socket client = new Socket("localhost", 9017);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dOut = new DataOutputStream(baos);
        dOut.writeLong(10001L);

        OutputStream out = client.getOutputStream();
        out.write(baos.toByteArray());
        out.flush();

        InputStream in = client.getInputStream();
        DataInputStream dIn = new DataInputStream(in);
        long id = dIn.readLong();
        String name = dIn.readUTF();
        int age = dIn.readInt();

        User user = new User(id, name, age);
        System.out.println(user);

        dOut.close();
        client.close();
    }

}
