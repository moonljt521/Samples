package com.example.enum_test;

/**
 * author: moon
 * created on: 17/11/3 下午3:57
 * description:
 */
public enum Car {

    BMW{
        String getPrice(String type){

            return type+"bmw";
        }
    };


    abstract String getPrice(String type);

    public static void main(String[] args) {

        for (Car car : Car.values()){

            System.out.println(">"+car.getPrice("a"));
        }


    }


}
