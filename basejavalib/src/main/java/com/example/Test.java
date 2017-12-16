package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author: moon
 * created on: 17/10/13 上午10:11
 * description:
 */
public class Test {
    public static void main(String[] args) {

//        Test test = new Test();
//
//        Object [] arr = {"lsgel" , 122, 2.2f,new Integer(1)};
//        for (Object o : arr){
//           test.write(o);
//        }
//
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//
//        long s = 499999999 * 499999999 ;
//        System.out.println("s = "+s);
//        Integer integer  = new Integer(12);
//        integer.intValue();
//
//        int a  = 0;


        String x = new String("goeasyway");
        change(x);
        System.out.println(x);


    }

    public static void change(String x) {
        x = "even";
    }


    public void write(String s){
        System.out.println("ssss");
    }

    public void write(int i){
        System.out.println("iiii");
    }

    public void write(double d){
        System.out.println("ddddd");
    }
    public void write(Object o){
        System.out.println(".....");
    }
}
