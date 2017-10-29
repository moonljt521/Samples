package com.example;

<<<<<<< HEAD
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

=======
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

        int i = 2;

        int a = i++;


        System.out.println("i = "+ i);
        System.out.println("a = "+ a);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.shutdown();

    }



>>>>>>> e9e4d1dced68645aac798db5aa45de75b091c3a0
}
