package com.example.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by moon on 2017/9/8.
 */

public class MainColl {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for (int i : list){
            System.out.println(i);
        }

        System.out.println("---------");


        Collections.swap(list,1,2);



        for (int i : list){
            System.out.println(i);
        }

    }
}
