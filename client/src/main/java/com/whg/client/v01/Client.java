package com.whg.client.v01;

import com.whg.api.User;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException{
        Socket client = new Socket("localhost", 9017);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dOut = new DataOutputStream(baos);
        dOut.writeLong(12345);

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

        System.out.println("v01 client find user success: user="+user);
    }

}
