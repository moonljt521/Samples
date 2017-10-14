package com.example.sort_algorithm;

import java.util.Arrays;

/**
 * author: moon
 * created on: 17/10/4 下午2:19
 * description: 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] a = {2, 4, 1, 3, 5, 22, 55, 11, 20, 49, 99, 7};

        System.out.println(Arrays.toString(a));

//        sort(a);
        insertSort(a);

        System.out.println(Arrays.toString(a));
    }

    private static void sort(int[] a) {

        int key;
        int index;

        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                key = a[i];
                for (index = i - 1; index >= 0 && a[index] > key; index--) {
                    a[index + 1] = a[index];
                }
                a[index + 1] = key;
            }
        }
    }

    private static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int currentValue = array[i];
            int position = i;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > currentValue) {
                    array[j + 1] = array[j];
                    position -= 1;
                } else {
                    break;
                }

            }
            array[position] = currentValue;
        }
    }
}
