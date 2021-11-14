package com.whg.client.v05;

import com.whg.api.UserService;
import com.whg.client.v04.UserServiceImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceFactory {

    public static UserService getUserService(){
        try {
            Object proxy = Proxy.newProxyInstance(
                    UserService.class.getClassLoader(),
                    new Class[]{UserService.class},
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            long id = (long) args[0];
                            UserService userService = new UserServiceImpl();
                            return userService.findUser(id);
                        }
                    });
            return (UserService) proxy;
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

}
