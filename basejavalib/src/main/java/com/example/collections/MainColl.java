package com.example.collections;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static sun.util.calendar.CalendarUtils.mod;

/**
 * Created by moon on 2017/9/8.
 */

public class MainColl {

    public static void main(String[] args) {
            MainColl coll = new MainColl();


//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//
//        for (int i : list){
//            System.out.println(i);
//        }
//
//        System.out.println("---------");
//
//
//        Collections.swap(list,1,2);
//
//
//
//        for (int i : list){
//            System.out.println(i);
//        }
//
//        Map<String,Integer> map = new HashMap<>();
//        String s = "1";
//        map.put(s,1);
//        map.put(s,1);
//        map.put(s,1);
//        map.put(s,4);
//        map.put(null,4);
//
//        System.out.println("size = "+map.size());
//
//        Iterator<Map.Entry<String,Integer>> iterator =  map.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry<String,Integer> entry = iterator.next();
//            System.out.println( entry.getKey());
//            System.out.println( entry.getValue());
//        }
//        System.out.println("---------");
//
//        int a = -12,b= 5;
//
//        System.out.println(mod(a,b));
//        System.out.println(a%b);


        coll.sequentialSearch();

    }


    // 顺序查找
    public void sequentialSearch(){

        int [] arr = {1,3,4,56,5,55};

        int key = 55;
//        arr[0] = key;
        int i = arr.length - 1;
        while (arr[i]!=key){
            i--;
        }

        System.out.println("索引为： "+ i);
    }
}




