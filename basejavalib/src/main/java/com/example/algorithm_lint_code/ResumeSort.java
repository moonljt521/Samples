package com.example.algorithm_lint_code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moon on 2017/12/26.
 * <p>
 * 恢复排序
 */

public class ResumeSort {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(1);
        list.add(2);
        list.add(3);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("ori->" + list.get(i));
        }

        new ResumeSort().recoverRotatedSortedArray(list);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public void recoverRotatedSortedArray(List<Integer> nums) {

        int temp = nums.get(0);
        int i;
        for (i = 0; i < nums.size(); i++) {
            if (nums.get(i) < temp)//找到最小的数
                break;
        }
        if (i != nums.size()) {
            for (int j = 0; j < i; j++){
                nums.add(nums.get(j));
            }
            nums.subList(0, i).clear();
        }

    }
}
