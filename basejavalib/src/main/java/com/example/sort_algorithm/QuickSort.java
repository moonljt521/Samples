package com.example.sort_algorithm;

import java.util.Arrays;

/**
 * author: moon
 * created on: 17/9/21 下午5:57
 * description:  快速排序： 平均时间复杂度 nlogn
 */
public class QuickSort {

    public static void main(String[] args) {

//        /        int d = x + (int)(Math.random() * (y - x));
//             int[] arr = {13, 32, 5, 10,1, 6, 10, 22, 13};
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = i + (0 + (int) (Math.random() * 10000));
            arr[i] = i;
        }

//        int [] arr = {2,14,5,6,8,1};


        long startTime = System.currentTimeMillis();
        System.out.println("start：" + startTime);


        // ------------
//        qsort(arr, 0, arr.length - 1);
        // ------------

        quickSort(arr);


        System.out.println("end:" + (System.currentTimeMillis() - startTime));

//        System.out.println(Arrays.toString(arr));

    }

    /**
     * 快排算法
     *
     * @param arr
     * @param low
     * @param high
     */
    public static int[] qsort(int[] arr, int low, int high) {
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
            while (low < high && arr[high] >= pivot) {
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


    // -------------
    public static void quickSort(int[] a) {
        if(a.length>0) {
            quickSort(a, 0 , a.length-1);
        }
    }

    private static void quickSort(int[] a, int low, int high) {
        //1,找到递归算法的出口
        if( low > high) {
            return;
        }
        //2, 存
        int i = low;
        int j = high;
        //3,key
        int key = a[ low ];
        //4，完成一趟排序
        while( i< j) {
            //4.1 ，从右往左找到第一个小于key的数
            while(i<j && a[j] > key){
                j--;
            }
            // 4.2 从左往右找到第一个大于key的数
            while( i<j && a[i] <= key) {
                i++;
            }
            //4.3 交换
            if(i<j) {
                int p = a[i];
                a[i] = a[j];
                a[j] = p;
            }
        }
        // 4.4，调整key的位置
        int p = a[i];
        a[i] = a[low];
        a[low] = p;
        //5, 对key左边的数快排
        quickSort(a, low, i-1 );
        //6, 对key右边的数快排
        quickSort(a, i+1, high);
    }


}
