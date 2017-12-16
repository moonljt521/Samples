package com.example.lint_code;

/**
 * Created by moon on 2017/12/15.
 *  对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。
 *  如果不存在，则返回 -1。
 *
 * 如果 source = "source" 和 target = "target"，返回 -1。

    如果 source = "abcdabcdefg" 和 target = "bcd"，返回 1。
 *
 */
public class StrSource_Target  {

    public static void main(String[] args) {

//        int index = new StrSource_Target().strStr("abcdabcdefg","bcd");
        int index = new StrSource_Target().strStr("aicbbice","ice");

        System.out.println(">>"+index);

        String x = "pepep";
        System.out.println(x.charAt(0));
    }


    /*
    * @param source: source string to be scanned.
    * @param target: target string containing the sequence of characters to match
    * @return: a index to the first occurrence of target in source, or -1  if target is not part of source.
    */
    public int strStr(String source, String target) {
        // write your code here
        if (source == null || target == null) {
            return -1;
        }

        int i, j;
        for (i = 0; i < source.length() - target.length() + 1; i++) {
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }
            if (j == target.length()) {
                return i;
            }
        }
        return -1;
    }
}
