package com.whg.client.v10;

import com.whg.api.UserService;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.Arrays;

public class ServiceFactory {

    public static <T> T getService(Class<T> interfaceClass){
        Object proxy = Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) {
                        // 过滤掉IDEA在debug的时候出现的代理自动调用toString方法
                        if(method.getName().contains("toString")){
                            return interfaceClass.getName();
                        }

                        beforeInvoke(proxy, method, args);

                        Object result = null;
                        Exception exception = null;
                        try {
                            result = doInvoke(interfaceClass, method, args);
                        } catch (Exception e) {
                            e.printStackTrace();
                            exception = e;
                        }

                        afterInvoke(result, exception);
                        return result;
                    }
                });
        return (T) proxy;
    }

    private static Object doInvoke(Class<?> interfaceClass, Method method, Object[] args) throws Exception{
        Socket client = new Socket("localhost", 9017);
        OutputStream out = client.getOutputStream();
        ObjectOutputStream oOut = new ObjectOutputStream(out);

        String methodName = method.getName();
        Class<?>[] paramTypes = method.getParameterTypes();
        oOut.writeObject(interfaceClass);
        oOut.writeUTF(methodName);
        oOut.writeObject(paramTypes);
        oOut.writeObject(args);
        oOut.flush();

        InputStream in = client.getInputStream();
        ObjectInputStream oIn = new ObjectInputStream(in);
        Object result = oIn.readObject();
        client.close();

        return result;
    }

    private static void beforeInvoke(Object proxy, Method method, Object[] args){
        System.out.println();
        System.out.println("执行前");
        System.out.println("执行类="+proxy.getClass().getName());
        System.out.println("执行接口="+ Arrays.toString(proxy.getClass().getInterfaces()));
        System.out.println("执行方法="+method.getName());
        System.out.println("执行参数="+Arrays.toString(args));
        System.out.println();
    }

    private static void afterInvoke(Object result, Exception exception){
        System.out.println();
        System.out.println("执行后");
        System.out.println("执行结果="+result);
        System.out.println("执行异常="+exception);
        System.out.println();
    }

}
