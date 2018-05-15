package com.example.find_algorithm;

/**
 * author: moon
 * created on: 18/4/11 上午10:03
 *
 * description:  是给出一个字符串，判断字符串中最长对称字串的长度。
 */
public class FindMaxDuichenSequence {

    public static void main(String[] args) {

        String sour = "121212abbcbba030";

        System.out.println(MaxSubStr(sour));


    }


        //判断对称的方法
        public static boolean duichen(String str){
            char[] chr = str.toCharArray();
            int min = 0;
            int max = chr.length-1;
            while(min<max){
                if(chr[min++]!=chr[max--])
                    return false;
            }
            return true;
        }
        //返回最长对称数组
        public static String MaxSubStr(String str){
            String substr="";
            for (int i = str.length(); i>=2; i--)
                for(int j = 0;j < i-1;j++)
                {
                    String temp = str.substring(j, i);
                    if(duichen(temp)&&substr.length()<temp.length())
                        substr = temp;
                }
            return substr;
        }



}
