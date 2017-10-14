package com.example.Threadlocaldemo;

/**
 * author: moon
 * created on: 17/10/10 下午5:11
 * description:
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal<Integer> a = new ThreadLocal<Integer>(){

            @Override
            protected Integer initialValue() {
                System.out.println("initvluae");
                return Integer.valueOf(0);
            }
        };


//        a.set(Integer.valueOf(1));

        System.out.println(">>"+a.get());
    }




}
