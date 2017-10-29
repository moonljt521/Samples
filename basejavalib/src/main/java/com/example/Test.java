package com.example;

/**
 * Created by moon on 2017/10/8.
 */

public class Test {

    public static void main(String[] args) {
        Test test = Test.getInstance();
        System.out.println("222");
        System.out.println(test);
    }


    public static Test getInstance(){
        return TestHolder.INSTANCE;
    }

    private Test(){

    }

    private static class TestHolder{
        private TestHolder(){
            System.out.println("11111");
        }


        static final Test INSTANCE = new Test();
    }

}
