package com.example.algorithm_mere2linkedlist;

import java.util.Arrays;

/**
 * author: moon
 * created on: 17/10/11 下午3:28
 * description: 合并有序链表
 */
public class Merge2LinkelistTest {

    public static void main(String[] args) {
        int[] a = { 1, 2, 3,4,5,6,7,8, 19 };

        int[] b = { 8,  9, 12, 25 };

        int[] c = merge(a, b);

        System.out.println(Arrays.toString(c));
    }


    public static int[] merge(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];

        int i, j, k;

        i = 0;

        j = 0;

        k = 0;

        while (i < a.length && j < b.length) {

            if (a[i] < b[j]) {

                result[k++] = a[i];

                i++;

            } else {

                result[k++] = b[j];

                j++;

            }

        }

        while (i < a.length) { // a有剩余

            result[k++] = a[i];

            i++;

        }

        while (j < b.length) { // b有剩余

            result[k++] = b[j];

            j++;

        }



        return result;

    }

}
