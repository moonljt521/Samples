package com.example.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by moon on 2017/11/20.
 * 测试主类
 */
public class TestDynaProxy implements Shopping {

    public static void main(String[] args) {

        TestDynaProxy me = new TestDynaProxy();

        MyInvocationHandler handler = new MyInvocationHandler(me);

        Shopping me1 = (Shopping) Proxy.newProxyInstance(me.getClass().getClassLoader(), me.getClass().getInterfaces()
            ,handler);

        System.out.println(me1.getClass().getName());
        System.out.println(me.getClass().getName());

        me1.buy(1000);
    }

    @Override
    public void buy(long money) {
        System.out.println("买了" + money + "dollar");
    }


}

/**
 *  购物接口
 */
interface Shopping {

    void buy(long money);

}

/**
 *  代理类
 */
class MyInvocationHandler implements InvocationHandler {

    private Object o;

    public MyInvocationHandler(Object o) {
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("buy".equals(method.getName())){
            Object result = method.invoke(o, args);
            return result;
        }
        return null;

    }
}