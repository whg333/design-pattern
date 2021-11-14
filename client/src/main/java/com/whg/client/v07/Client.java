package com.whg.client.v07;

import com.whg.api.User;
import com.whg.api.UserService;

public class Client {

    public static void main(String[] args) {
        UserService userService = ProxyFactory.getProxy(new UserServiceProxy());
        User user = userService.findUser(12345);
        System.out.println("v07 client receive from server: user="+user);
    }

}
