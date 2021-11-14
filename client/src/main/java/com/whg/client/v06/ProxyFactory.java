package com.whg.client.v06;

import com.whg.api.UserService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static UserService getProxy(){
        try {
            InvocationHandler invoker = new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    UserService userService = new UserServiceProxy();
                    Object result = method.invoke(userService, args);
                    return result;
                }
            };
            Object proxy = Proxy.newProxyInstance(UserService.class.getClassLoader(),
                    new Class[]{ UserService.class }, invoker);
            return (UserService)proxy;
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

}
