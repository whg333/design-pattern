package com.whg.client.v05;

import com.whg.api.UserService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceFactory {

    public static UserService getService(Object target){
        try {
            Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
            Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
            UserService proxyInst = (UserService) constructor.newInstance(new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object result = method.invoke(target, args);
                    return result;
                }
            });
            return proxyInst;
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

}
