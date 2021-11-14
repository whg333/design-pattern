package com.whg.client.v02;

import com.whg.api.User;
import com.whg.api.UserService;

import java.io.*;
import java.net.Socket;

public class UserServiceImpl implements UserService {

    @Override
    public User findUser(long id) {
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
