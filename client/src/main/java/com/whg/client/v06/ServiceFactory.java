package com.whg.client.v06;

import com.whg.api.UserService;
import com.whg.client.v04.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceFactory {

    public static UserService getUserService(){
        UserService userService = new UserServiceImpl();
        return (UserService) getService(userService);
    }

    private static Object getService(Object target){
        try {
            Object proxy = Proxy.newProxyInstance(
                    target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            Object result = method.invoke(target, args);
                            return result;
                        }
                    });
            return proxy;
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

}
