package com.example.print_graphical;

/**
 * Created by moon on 2017/12/22.
 *   打印个三角形
 */

public class PrintTriangle {

    public static void main(String[] args) {

        printTriangle();

        printRightTriangle();
    }


    //打印一个等边三角形
    private static void printTriangle(){

        for (int i = 1;i< 6 ;i++){

            for (int j = 5-i; j >=0 ;j --){

                System.out.print(" ");

            }
            for (int k =1; k<=2*i-1 ; k++){
                System.out.print("*");
            }

            System.out.println();

        }

    }

    /**
     * 打印直角三角形
     */
    private static void printRightTriangle(){

        for (int i = 1; i<8 ;i++){
            for (int j = 1; j< i+1 ;j++){
                System.out.print("* ");
            }

            System.out.println();
        }

    }

}
