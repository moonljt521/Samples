package com.example.algorithm_lint_code;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by moon on 2017/12/28.
 * <p>
 * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
 * <p>
 * 你需要实现的函数twoSum需要返回这两个数的下标,
 * 并且第一个下标小于第二个下标。
 * 注意这里下标的范围是 0 到 n-1。
 * <p>
 * 例如：给出 numbers = [2, 7, 11, 15], target = 9, 返回 [0, 1].
 */

public class TwoSum {

    public static void main(String[] args) {

        int[] a = {2,7,4,5,6};

        System.out.println(Arrays.toString(twoSum(a,9)));

    }


    /*
    * @param numbers: An array of Integer
    * @param target: target = numbers[index1] + numbers[index2]
    * @return: [index1 + 1, index2 + 1] (index1 < index2)
    */
    public static int[] twoSum(int[] numbers, int target) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i + 1);
        }

        for (int i = 0; i < numbers.length; i++) {
            int value = target - numbers[i];
            if (map.containsKey(value) && map.get(value) != i + 1) {
                int index = map.get(value);
                if (i + 1 < index) {


                    return new int[]{i , index -1};
                }

                return new int[]{index -1 , i };
            }
        }
        return new int[0];

    }

}
