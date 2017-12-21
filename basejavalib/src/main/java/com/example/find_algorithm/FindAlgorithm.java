package com.example.find_algorithm;

/**
 * author: moon
 * created on: 17/9/21 下午12:29
 * description:  查找算法，常用的包括1顺序查找，2二分查找，3分块查找
 */
public class FindAlgorithm {

    public static void main(String[] args) {

        System.out.println("~~~~~");

        FindAlgorithm findAlgorithm = new FindAlgorithm();

//        int[] arr = {3,4,5,8,8,8,8,10,13,14};
        int[] arr = {4,5,9,9,12,13,14,15,15,18};
        int searchKey = 10 ;

        findAlgorithm.binarySearch(arr,searchKey);
    }

    /**
     * 顺序查找
     */
    private void frequencySearch(){
        //略....
    }
    /**
     * 二分查找  这个方法因为不能对含有重复数字的数组起作用，所以弃用 ！！！！！！
     * @param arr
     * @param key
     */
    @Deprecated
    private void binarySearch1(int[] arr, int key){

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

    /**
     * 对于数据组中含有重复数据的也能有作用
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch(int[] nums, int target) {
        //write your code here
        if (nums == null || nums.length == 0){
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        int middle = - 1;

        while (low <= high){

            middle = (high + low )/2;

            if ( target == nums[middle]){

                //你查到的数据不一定是第一次出现的
                while((middle>=1)&&(nums[middle]==nums[middle-1])){
                    middle--;
                }

                return middle ;
            }

            if ( target< nums[middle]){

                high = middle - 1;
            }else {
                low = middle + 1;
            }
        }

        //
        if (nums[middle] != target){
            return -1;
        }

        return middle;
    }



}
