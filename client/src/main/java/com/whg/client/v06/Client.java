package com.whg.client.v06;

import com.whg.api.User;
import com.whg.api.UserService;

public class Client {

    public static void main(String[] args) {
        UserService userService = ServiceFactory.getUserService();
        User user = userService.findUser(12345);
        System.out.println("v06 client find user success: user="+user);
    }

}
