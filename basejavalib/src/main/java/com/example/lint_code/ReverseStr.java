package com.example.lint_code;

/**
 * Created by moon on 2017/12/24.
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *  例如输入字符串 "ljt moon xiaot" ，输出 "xiaot moon ljt"
 */

public class ReverseStr {

    public static void main(String[] args) {

//        System.out.println();
        reverseWords("ljt moon xiaot");
    }



    /*
    * @param s: A string
    * @return: A string
    */
    public static String reverseWords(String s) {
        // write your code here
        if (s == null || s.length() == 0) {
            return "";
        }

        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = array.length - 1; i >= 0; --i) {
            if (!array[i].equals("")) {
                System.out.println(array[i]);
                sb.append(array[i]).append(" ");
            }
        }

        //remove the last " "
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}
