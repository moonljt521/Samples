package com.example.sort_algorithm;

import java.util.Arrays;

/**
 * author: moon
 * created on: 17/9/21 下午5:57
 * description:  快速排序： 平均时间复杂度 nlogn
 */
public class QuickSort {

    public static void main(String[] args) {

        int [] arr = {2,14,5,6,8,1};

        System.out.println(Arrays.toString(arr));

        long startTime = System.currentTimeMillis();
        System.out.println("start：" + startTime);


        // ------------
        qsort(arr, 0, arr.length - 1);
        // ------------

        System.out.println("end:" + (System.currentTimeMillis() - startTime));

        System.out.println(Arrays.toString(arr));

    }

    /**
     * 快排算法
     *
     * @param arr
     * @param low
     * @param high
     */
    public static int [] qsort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);        //将数组分为两部分
            qsort(arr, low, pivot - 1);                   //递归排序左子数组
            qsort(arr, pivot + 1, high);                  //递归排序右子数组
        }

        return arr;
    }

    /**
     * 计算枢轴
     *
     * @param arr
     * @param low
     * @param high
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];     //枢轴记录
        while (low < high) {
            while (low < high && arr[high] >= pivot){
                --high;
            }
            arr[low] = arr[high];             //交换比枢轴小的记录到左端
            while (low < high && arr[low] <= pivot) {
                ++low;
            }
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }


}
