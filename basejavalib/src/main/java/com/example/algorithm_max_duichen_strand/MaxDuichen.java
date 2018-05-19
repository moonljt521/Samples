package com.example.algorithm_max_duichen_strand;

/**
 * 求一个字符 的  最大对称串
 */
public class MaxDuichen {

    public static void main(String[] args) {

        String result = "abcdcba";

        System.out.println("--->" + isDuichen(result));


        System.out.println("最大对称串为： " + getMaxLength(result));
    }

    /**
     * 判断一个字符串是否对称
     *
     * @param res
     * @return
     */
    public static boolean isDuichen(String res) {
        if (res == null || res.length() == 0) {
            return false;
        }

        char[] c = res.toCharArray();

        int min = 0;

        int max = c.length - 1;

        while (min < max) {
            if (c[min++] != c[max--]) {
                return false;
            }
        }

        return true;
    }

    private static String getMaxLength(String result) {

        String s = "";

        for (int i = result.length(); i >= 2; i--) {

            for (int j = 0; j < i; j++) {

                String x = result.substring(j, i);

                if (isDuichen(x) && s.length() <= x.length()) {

                    s = x;

                }

            }

        }


        return s;
    }


}
