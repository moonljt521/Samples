package com.example.algorithm_lint_code;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moon on 2017/12/21.
 *
 *  给定一个列表，该列表中的每个要素要么是个列表，要么是整数。将其变成一个只包含整数的简单列表。
 *
 *  给定 [1,2,[1,2]]，返回 [1,2,1,2]。

    给定 [4,[3,[2,[1]]]]，返回 [4,3,2,1]。
 */

public class PlaneList {

    public static void main(String[] args) {

        PlaneList planeList = new PlaneList();





    }

    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        // Write your code here
        List<Integer> list = new ArrayList<>();

        flatMap(nestedList,list);

        return list;
    }

    private void flatMap(List<NestedInteger> nestedList,List<Integer> list){
        if (nestedList!=null){
            for (NestedInteger nestedInteger : nestedList){
                if (nestedInteger.isInteger()){
                    list.add(nestedInteger.getInteger());
                }else {
                    flatMap(nestedInteger.getList(),list);
                }
            }
        }
    }


    public interface NestedInteger {

        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();

    }


}


