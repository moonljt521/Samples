package com.example.algorithm;

/**
 * author: moon
 * created on: 17/9/21 下午12:29
 * description:  查找算法，常用的包括1顺序查找，2二分查找，3分块查找
 */
public class FindAlgorithm {

    public static void main(String[] args) {

        System.out.println("~~~~~");

        FindAlgorithm findAlgorithm = new FindAlgorithm();

        int[] arr = {1,2,4,6,13,22,111,136};
        int searchKey = 23;

        findAlgorithm.binarySearch(arr,searchKey);
    }

    /**
     * 顺序查找
     */
    private void frequencySearch(){
        //略....
    }


    /**
     * 二分查找
     * @param arr
     * @param key
     */
    private void binarySearch(int[] arr, int key){

        int low = 0;
        int high = arr.length -1;

        int fre = 0;
        int middle = -1;
        boolean has = false;

        while (low <= high){

            fre ++ ;

            middle = (low + high)/2;
            if (key == arr[middle]){

                has = true;
                break;
            }

            if (key < arr[middle]){
                high = middle - 1;
            }else {
                low = middle + 1;
            }
        }

        System.out.println( !has ? "没有找到" : "找到了");

        System.out.println("频次=" + fre);

        System.out.println("角标=" + middle);
    }




}
