package com.example.algorithm_many_sort;

import java.util.Arrays;

/**
 * Created by moon on 2017/12/12.
 *
 *    冒泡排序
 */

public class BubbleSort {

    public static void main(String[] args) {

        int score[] = {67, 69, 75, 87, 101,89, 90, 99, 12};
//        int score[] = {1, 2, 3, 4, 5,6, 7, 8, 12};

        System.out.println("排序前："+  Arrays.toString(score));

        sort(score);
//        bubbleSort2(score,score.length);

        System.out.println("排序后："+  Arrays.toString(score));


    }


    /**
     * 未优化
     * @param arr
     */
    private static void sort(int[] arr){

        if (arr == null || arr.length == 0 || arr.length == 1){
            return;
        }

        int count = 0;

        for (int i=0;i<arr.length; i++){

            for (int j = 1 ;j < arr.length - i  ; j ++){

                count ++ ;

                if (arr[j-1] > arr[j ]){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;


                }
            }
        }

        System.out.println("sort1 .. count = " + count);

    }





    public static void bubbleSort2(int [] a, int n){
        int count = 0;

        int j, k = n;
        boolean flag = true;//发生了交换就为true, 没发生就为false，第一次判断时必须标志位true。
        while (flag){
            flag=false;//每次开始排序前，都设置flag为未排序过
            for(j=1; j<k; j++){

                count ++;

                if(a[j-1] > a[j]){//前面的数字大于后面的数字就交换
                    //交换a[j-1]和a[j]
                    int temp;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j]=temp;

                    //表示交换过数据;
                    flag = true;



                }
            }
            k--;//减小一次排序的尾边界
        }//end while


        System.out.println("sort1 .. count = " + count);


    }//end


}
