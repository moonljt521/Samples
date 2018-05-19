package com.example.algorithm_lint_code;

import com.example.algorithm_many_sort.QuickSort;

import java.util.Arrays;

/**
 * Created by moon on 2017/12/13.
 * <p>
 * 在数组中找到第k大的元素
 * 给出数组 [9,3,2,4,8]，第三大的元素是 4
 * <p>
 * 给出数组 [1,2,3,4,5]，第一大的元素是 5，第二大的元素是 4，第三大的元素是 3，以此类推
 * 【 此题用冒泡排序效率太低了，  改用快排 】
 */

public class kthLargestElement {

    public static void main(String[] args) {

        int[] arr = {2, 14, 5, 6, 8, 1};

        int k = 2;

        System.out.println("排序前" + Arrays.toString(arr));

        int[] newarr = QuickSort.qsort(arr, 0, arr.length - 1);

        System.out.println("排序后" + Arrays.toString(newarr));

        System.out.println("第" + k + "大的数是 = " + new kthLargestElement().kthLargestElement(k, newarr));

    }


    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        if (k < 1) {
            return -1;
        }

        int[] arr = QuickSort.qsort(nums, 0, nums.length - 1);

        for (int i = 1; i <= arr.length; i++) {
            if (k == i) {
                return arr[nums.length - k];
            }
        }
        return 0;
    }




}
