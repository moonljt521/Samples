package com.example.sort_algorithm;

import java.util.Arrays;

/**
 * author: moon
 * created on: 17/9/21 下午5:57
 * description:  快速排序： 平均时间复杂度 nlogn
 */
public class QuickSort {

    public static void main(String[] args) {

//        int d = x + (int)(Math.random() * (y - x));
//        int[] arr = {13, 32, 5, 10,1, 6, 10, 22, 13};
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
//            arr[i]=i+ (0 + (int)(Math.random()*1000));
            arr[i] = i;
        }

        System.out.println(Arrays.toString(arr));

        long startTime = System.currentTimeMillis();
        System.out.println("start：" + startTime);

        sort(arr);

        System.out.println("end:" + (System.currentTimeMillis() - startTime));

        System.out.println(Arrays.toString(arr));

    }

    private static void sort(int[] a) {
        qsort(a, 0, a.length - 1);
    }

    /**
     * 快排算法
     *
     * @param arr
     * @param low
     * @param high
     */
    private static void qsort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);        //将数组分为两部分
            qsort(arr, low, pivot - 1);                   //递归排序左子数组
            qsort(arr, pivot + 1, high);                  //递归排序右子数组
        }
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
            while (low < high && arr[high] >= pivot) --high;
            arr[low] = arr[high];             //交换比枢轴小的记录到左端
            while (low < high && arr[low] <= pivot) ++low;
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }


}
