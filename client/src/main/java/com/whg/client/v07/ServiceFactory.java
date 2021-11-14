package com.whg.client.v07;

import com.whg.api.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceFactory {

    public static UserService getProxy(Object target){
        try {
            InvocationHandler invoker = new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object result = method.invoke(target, args);
                    return result;
                }
            };
            Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                    target.getClass().getInterfaces(), invoker);
            return (UserService)proxy;
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

}
