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

        int i = 2;

        int a = i++;


        System.out.println("i = "+ i);
        System.out.println("a = "+ a);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.shutdown();

    }



}
