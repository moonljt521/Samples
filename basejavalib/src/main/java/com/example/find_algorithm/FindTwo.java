package com.example.find_algorithm;

/**
 * author: moon
 * created on: 17/12/15 下午2:40
 * description:  没用的 测试类
 */
public class FindTwo {


    public static void main(String[] args) {

        int [] arr = {3,4,11,15,21,88};

        int key = 21;


        FindTwo findTwo = new FindTwo();


    }


    public void findAlgorithm(int [] arr, int key){

        if (arr == null || arr.length == 0 ){

            return;
        }

        int low = 0;
        int high = arr.length - 1;

        int index ;

        int middle = -1 ;

        while (low < high){

            middle = (low + high )/2 ;

            if (key == arr[middle]){

                index = middle;

                break;
            }

            if ( key < arr[middle]){
                high = middle - 1;
            }else {
                low = middle + 1;
            }

        }




    }







}
