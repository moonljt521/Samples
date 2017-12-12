package com.example.sort_algorithm;

import java.util.Arrays;

/**
 * Created by moon on 2017/12/12.
 *
 *    冒泡排序
 */

public class BubbleSort {

    public static void main(String[] args) {

//        int score[] = {67, 69, 75, 87, 101,89, 90, 99, 12};
        int score[] = {67, };

        System.out.println("排序前："+  Arrays.toString(score));

        sort(score);

        System.out.println("排序后："+  Arrays.toString(score));


    }


    private static void sort(int[] arr){

        if (arr == null || arr.length == 0 || arr.length == 1){
            return;
        }

        for (int i=0;i<arr.length-1; i++){
            for (int j = 0 ;j < arr.length - i - 1 ; j ++){
                if (arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = temp;

                }
            }
        }

    }


}
