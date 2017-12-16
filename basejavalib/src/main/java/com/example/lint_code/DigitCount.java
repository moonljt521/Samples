package com.example.lint_code;

/**
 * author: moon
 * created on: 17/12/11 下午6:28
 * description:  【中等】
 *               计算数字k在0到n中的出现的次数，k可能是0~9的一个值
 *               例如n=12，k=1，在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]，我们发现1出现了5次 (1, 10, 11, 12)
 */
public class DigitCount {

    public static void main(String[] args) {

//        int count = new DigitCount().digitCounts(1,12);
        int count = new DigitCount().get(0, 19);

        System.out.println("k的个数：" + count);
    }

    public int digitCounts(int k, int n) {
        // write your code here
        if (n == 1) {
            return 1;
        }

        int count = 0;

        for (int i = 0; i < n + 1; i++) {

            String ii = i + "";
            if (ii.length() > 1) {
                String[] arr = ii.split("");
                for (int y = 0; y < arr.length; y++) {
                    if (arr[y].contains(k + "")) {
                        count++;
                    }
                }

            } else {
                if (ii.contains(k + "")) {
                    System.out.println("i = " + i + ", k = " + k);
                    count++;
                }
            }
        }
        return count;
    }

    public int get(int k, int n) {

        int count = 0, singlecount = 0;
        int p = n;
        int num = 0;//表示处理数字的位数 num=0表示处理个位 1表示十位等

        if (k == 0) {
            return (n / 10) + 1;
        }
        if (n == 0) {
            return 0;
        }

        while (p != 0) {
            if ((p % 10) > k){
                singlecount = (int) ((p / 10 + 1) * (Math.pow(10, (double) num)));
            }
            if ((p % 10) == k)
            {
                singlecount = (int) ((p / 10) * (Math.pow(10, (double) num)) + (n % ((Math.pow(10, (double) num)))) + 1);
            }
            if ((p % 10) < k) {
                singlecount = (int) ((p / 10) * (Math.pow(10, (double) num)));
            }
            count += singlecount;
            p = p / 10;
            num++;
        }
        return count;
    }

}
