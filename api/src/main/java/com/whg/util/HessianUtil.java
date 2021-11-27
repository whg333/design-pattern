package com.whg.util;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.whg.api.Product;
import com.whg.api.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class HessianUtil {

    public static void main(String[] args) throws IOException {
        Object product = new Product(3, "3", "小米");
        byte[] bytes = HessianUtil.toByteArray(product);
        product = HessianUtil.parseFrom(bytes);
        System.out.println(product);

        Object user = new User(12345L, "test", 22);
        bytes = HessianUtil.toByteArray(user);
        System.out.println("user bytes="+ Arrays.toString(bytes));
        user = HessianUtil.parseFrom(bytes);
        System.out.println(user);
    }

    public static byte[] toByteArray(Object object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(baos);
        out.writeObject(object);
        out.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        out.close();
        return bytes;
    }

    public static Object parseFrom(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Hessian2Input in = new Hessian2Input(bais);
        Object object = in.readObject();
        bais.close();
        in.close();
        return object;
    }

}
