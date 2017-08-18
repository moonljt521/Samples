package com.example.annotation;

import com.example.annotation.runtime.Guest;

/**
 * author: moon
 * created on: 17/8/16 上午11:19
 * description:
 */
public class Test {

    public static void main(String[] args){
        System.out.println("start...");

        Guest guest = new Guest();

        guest.printValue(guest);

        System.out.println("看看有没有设置进去 = "+guest.name);

    }
}
