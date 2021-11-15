package com.whg.client.v09;

import com.whg.api.User;
import com.whg.api.UserService;

public class Client {

    public static void main(String[] args) {
        UserService userService = ServiceFactory.getUserService();
        User user = userService.findUser(12345);
        System.out.println("v09 client find user success: user="+user);

        user.setAge(35);
        if(userService.saveUser(user)){
            System.out.println("v09 client save user success: user="+user);
        }
    }

}
