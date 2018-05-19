package com.example.algorithm_many_sort;

/**
 * author: moon
 * created on: 17/9/5 下午4:55
 * description:  归并排序 ****
 */
public class MergeSort {

    public static void main(String[] args) {

        int a[] = {0,1,2,3,4,5,6,7,8,9,10};
        int al = 11;
        int b[] = {11,12,13,14,15,16,17,18,19};
        int bl = 9;

        int [] res = new int[20];

        new MergeSort().merge(a,al-1,b,bl-1,res);

        for (int x : res){
            System.out.println(x);
        }

    }

    public void merge(int[] a, int alentth, int [] b, int blength, int [] result ){

        if (a ==null || b == null){
            return;
        }

        int i =0,j=0,k = 0;
        while (i <= alentth && j<= blength){
            if (a[i]< b[j]){
                result [k++] = a[i++];
            }else {
                result [k++] = b[j++];
            }
        }

        while (i<= alentth){
            result [k++] = a[i++];
        }

        while (j<= blength){
            result [k++] = b[j++];
        }



    }

}
