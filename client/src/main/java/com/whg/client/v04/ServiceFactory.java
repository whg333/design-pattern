package com.whg.client.v04;

import com.whg.api.UserService;

import java.lang.reflect.*;

public class ServiceFactory {

    public static UserService getUserService(){
        try {
            Class<?> proxyClass = Proxy.getProxyClass(
                    UserService.class.getClassLoader(),
                    UserService.class);
            Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
            Object proxy = constructor.newInstance(new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    UserService userService = new UserServiceImpl();
                    Object result = method.invoke(userService, args);
                    return result;
                }
            });
            return (UserService) proxy;
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

}
