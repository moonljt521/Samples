package com.example.classloader;

/**
 * Created by moon on 2017/11/22.
 */

public class TestClassLoader {

    public static void main(String[] args) {
        System.out.println(SubClass.a);

        System.out.println(SuperClass.a);



        new SubClass();
    }

}


class SuperClass {

    static {
        System.out.println("superClass init");
    }

    public static final String a = "a";

}

class SubClass extends SuperClass{

    static {
        System.out.println("SubClass init");
    }
}
