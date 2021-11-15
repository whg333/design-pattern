package com.whg.client.v10;

import com.whg.api.Product;
import com.whg.api.ProductService;
import com.whg.api.User;
import com.whg.api.UserService;

public class Client {

    public static void main(String[] args) {
        UserService userService = ServiceFactory.getService(UserService.class);
        User user = userService.findUser(12345);
        System.out.println("v10 client find user success: user="+user);

        user.setAge(35);
        if(userService.saveUser(user)){
            System.out.println("v10 client save user success: user="+user);
        }

        ProductService productService = ServiceFactory.getService(ProductService.class);
        Product product = productService.findProduct("2");
        System.out.println("v10 client find product success: product="+product);
    }

}
