package com.whg.client.v03;

import com.whg.api.UserService;
import com.whg.client.v02.UserServiceImpl;

public class ServiceFactory {

    public static UserService getUserService(){
        return new UserServiceImpl();
    }

}
