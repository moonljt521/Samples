package com.example.sort_algorithm;

/**
 * author: moon
 * created on: 17/8/24 下午7:08
 * description: 递归
 */
public class Recursion {

    public static void main(String[] args) {
        System.out.println(new Recursion().getF(3));
    }

    public int getF(int i){
        if (i<2){
            return i==0 ? 0 : 1;
        }
        return getF(i-1) + getF(i-2);
    }



}
