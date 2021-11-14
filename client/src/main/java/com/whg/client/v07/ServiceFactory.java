package com.whg.client.v07;

import com.whg.api.UserService;
import com.whg.client.v04.UserServiceImpl;

import java.lang.reflect.*;
import java.util.Arrays;

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
            return proxy;
        } catch (Exception  e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void beforeInvoke(Object proxy, Method method, Object[] args){
        System.out.println("执行前");
        System.out.println("执行类="+proxy.getClass().getName());
        System.out.println("执行接口="+Arrays.toString(proxy.getClass().getInterfaces()));
        System.out.println("执行方法="+method.getName());
        System.out.println("执行参数="+Arrays.toString(args));
        System.out.println();
    }

    private static void afterInvoke(Object result, Exception exception){
        System.out.println("执行后");
        System.out.println("执行结果="+result);
        System.out.println("执行异常="+exception);
        System.out.println();
    }

}
