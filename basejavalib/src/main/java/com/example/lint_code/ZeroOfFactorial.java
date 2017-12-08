package com.example.lint_code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: moon
 * created on: 17/12/8 下午5:43
 * description:  设计一个算法，计算出n阶乘中尾部零的个数
 */
public class ZeroOfFactorial {

    public static void main(String[] args) {

//        System.out.println("0的个数 = " + trailingZeros(105) );
        System.out.println("0的个数 = " + trailingZerosLast(105) );



//        System.out.println("0的个数 = " + trailingZeros2(105) );

    }


    /**
     * lambda 形式
     * @param n
     * @return
     */
    @Deprecated
    public static long trailingZeros1(long n) {
        // write your code here, try to do it without arithmetic operators.

        long jiecheng = 1;
        for (long i = 1 ;i< n + 1  ; i++ ){
            jiecheng *= i;
        }
        String x = jiecheng+"";
        String [] str = x.split("");
        long count = Arrays.stream(str).filter( y -> y.equals("0")).collect(Collectors.toList()).size();

        return count;
    }

    /**
     * 普通 for 循环
     * @param n
     * @return
     */
    @Deprecated
    public static long trailingZeros2(long n) {
        // write your code here, try to do it without arithmetic operators.

        long jiecheng = 1;
        for (long i = 1 ;i< n + 1  ; i++ ){
            jiecheng *= i;
        }
        String x = jiecheng+"";
        String [] str = x.split("");

        List<Long> list = new ArrayList<>();
        for (long i = 0;i < str.length ;i ++){
            list.add(i);
        }

        long count =0;
        for (int i = 0;i < (long) str.length ; i ++){
            if (str[i].equals("0")){
                count ++ ;
            }
        }
        return count;
    }

    /**
     * @param n
     * @return
     */
    public static long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        long start = System.currentTimeMillis();
        long count = 0;
        long pwr = 25;
        for (long temp = 5; temp <= n; temp+=5) {
            // for循环内部的temp都是5的倍数，因此首先进行+1操作
            count++;
            pwr = 25;
            // 判断是不是25、125、625...的倍数，并根据每次pwr的变化进行+1操作
            while (temp % pwr == 0) {
                count++;
                pwr *= 5;
            }
        }

        System.out.println("during time = " + (System.currentTimeMillis() - start));
        return count;
    }


    /**
     * @param n
     * @return
     */
    public static long trailingZerosLast(long n) {
        // write your code here, try to do it without arithmetic operators.
        long start = System.currentTimeMillis();
        long count = 0;
        long temp=n/5;
        while (temp!=0) {
            count+=temp;
            temp/=5;
        }

        System.out.println("during time = " + (System.currentTimeMillis() - start));
        return count;
    }

}
