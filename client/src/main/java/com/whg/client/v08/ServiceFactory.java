package com.whg.client.v08;

import com.whg.api.UserService;

import java.lang.reflect.*;
import java.util.Arrays;

public class ServiceFactory {

    public static UserService getService(Object target){
        try {
            Class<?> proxyClass = Proxy.getProxyClass(target.getClass().getClassLoader(), target.getClass().getInterfaces());
            Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
            UserService proxyInst = (UserService) constructor.newInstance(new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) {
                    beforeInvoke(proxy, method, args);

                    Object result = null;
                    Exception exception = null;
                    try {
                        result = method.invoke(target, args);
                    } catch (Exception e) {
                        exception = e;
                    }

                    afterInvoke(result, exception);
                    return result;
                }
            });
            return proxyInst;
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void beforeInvoke(Object proxy, Method method, Object[] args){
        System.out.println("\n执行前");
        System.out.println("执行类="+proxy.getClass().getName());
        System.out.println("执行接口="+Arrays.toString(proxy.getClass().getInterfaces()));
        System.out.println("执行方法="+method.getName());
        System.out.println("执行参数="+Arrays.toString(args));
    }

    private static void afterInvoke(Object result, Exception exception){
        System.out.println("\n执行后");
        System.out.println("执行结果="+result);
        System.out.println("执行异常="+exception);
    }

}
