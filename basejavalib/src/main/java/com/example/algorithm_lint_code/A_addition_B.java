package com.example.algorithm_lint_code;

import java.util.ArrayList;
import java.util.List;

/**
 * author: moon
 * created on: 17/12/8 下午4:16
 * description:  给出两个整数a和b, 求他们的和, 但不能使用 + 等数学运算符。
 */
public class A_addition_B {

    public static void main(String[] args) {

//        System.out.println(plus(3,9));
        System.out.println(aplusb(3, 9));







//        System.out.println( Math.pow(2,3));    pow  即 求2的3次方

    }


    /**
     * 我自己想的方法
     *
     * @param a
     * @param b
     * @return
     */
    private static int plus(int a, int b) {


        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        int sum = list.stream().reduce((x, y) -> x + y).get();  // 出现了 + 号了 ~~~~~！！！！！
        return sum;
    }

    /**
     * @param a
     * @param b
     * @return 网上搜的方法
     */
    public static int aplusb(int a, int b) {
        // write your code here, try to do it without arithmetic operators.
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int sum, i;
        i = a ^ b;          // ^   --->  a,b 相同为0，不同为1
        sum = (a & b) << 1;   // &   ---> a和b 都为1 时才为1，其他情况都是 0
        return aplusb(sum, i);
    }


}
