package com.example.encode;

public class YiHuoJiaMi {

    public static void main(String [] args){

        String source = "I am a fu";

        System.out.println("加密前： "+ source);

        int key = 0x10;

        char[] chars = source.toCharArray();

        for (int i =0;i< chars.length ;i++){
            chars[i]= (char) (chars[i] ^ key);
        }

        String res = new String(chars);
        System.out.println("加密后： "+res);

        char [] chars1 = res.toCharArray();
        for (int j = 0; j < chars1.length ; j++){
            chars1[j]= (char) (chars1[j] ^ key);
        }

        System.out.println("解密后：" + new String(chars1));


        short p = 12;
        System.out.println( p ^ key);
        System.out.println( p ^ key ^ key);

        


    }
}
