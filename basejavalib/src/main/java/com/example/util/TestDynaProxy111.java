package com.example.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by moon on 2017/11/20.
 * 测试主类
 */
public class TestDynaProxy111 {

    public static void main(String[] args) {

        // 被代理对象
        Impl me = new Impl();

        MyInvocationHandler handler = new MyInvocationHandler(me);

//        Shopping me1 = (Shopping) Proxy.newProxyInstance(me.getClass().getClassLoader(), me.getClass().getInterfaces()
        Shopping me1 = (Shopping) Proxy.newProxyInstance(me.getClass().getClassLoader(), new Class[]{me.getClass()}
            ,handler);

        System.out.println(me1.getClass().getName());
        System.out.println(me.getClass().getName());

        me1.buy(1000);

        me1.sell();
    }
}

class Impl extends Shopping {

    @Override
    public void buy(long money) {
        System.out.println("买了" + money + "dollar");
    }
    @Override
    public void sell() {
        System.out.println("sell...");
    }
}

//class Impl implements Shopping {
//
//    @Override
//    public void buy(long money) {
//        System.out.println("买了" + money + "dollar");
//    }
//    @Override
//    public void sell() {
//        System.out.println("sell...");
//    }
//}

/**
 *  接口
 */
abstract class Shopping {

    abstract void buy(long money);

    abstract void sell();

}

/**
 *  接口
 */
//interface Shopping {
//    void buy(long money);
//    void sell();
//}

/**
 *  proxy
 */
class MyInvocationHandler implements InvocationHandler {

    private Object o;

    public MyInvocationHandler(Object o) {
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        if ("buy".equals(method.getName())){
//            Object result = method.invoke(o, args);
//            return result;
//        }
//        return null;

        Object result = method.invoke(o, args);
        return result;

    }
}