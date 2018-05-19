package com.example.algorithm_lint_code;

/**
 * author: moon
 * created on: 17/12/4 上午11:23
 * description: 斐波那契数列
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fibonacci(1));
    }


    public int fibonacci(int n) {
        // write your code here
        if(n == 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        return fibonacci(n-1)+ fibonacci(n-2);
    }

}
