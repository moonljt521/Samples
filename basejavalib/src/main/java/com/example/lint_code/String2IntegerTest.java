package com.example.lint_code;

/**
 * author: moon
 * created on: 17/10/11 下午2:20
 * description: 算法： String 转 Integer
 */
public class String2IntegerTest {

    public static void main(String[] args) {


        System.out.println(atoi(null));

    }


    public static int atoi(String str) {
        if (null == str || str.length() == 0)
            return 0;
        long sum = 0;
        str = str.trim();
        int len = str.length();
        boolean flag = false; //当出现第一个数字时，如果后续是数字则true,不是数字则为false
        boolean negative = false; //是否为负数的判断
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                if (flag ) {   //当遇到数字后，中途再次遇到的是非数字，则将之前的值返回
                    return isOutOfRange(sum);
                }
                continue;
            }

            if (Character.isDigit(c)) { //判断是否为数字
                flag = true;
                //当遇到第一个数字时，如果其前面是的两个字符是“+” “-” “ ” 的组合，则为非法字符，返回0
                if (i - 2 >= 0 && (str.charAt(i - 1) == '+'
                        || str.charAt(i - 1) == '-' || str.charAt(i - 1) == ' ')
                        && (str.charAt(i - 2) == '+'|| str.charAt(i - 2) == '-'
                        || str.charAt(i - 2) == ' ')) {
                    return 0;
                } else if (i - 1 >= 0 && str.charAt(i - 1) == '+') { //如果前面只有一个“+” 则为正数
                    sum = sum * 10 + Integer.parseInt(String.valueOf(c));
                } else if (i - 1 >= 0 && str.charAt(i - 1) == '-') { //如果前面只有一个“-” 则为负数
                    negative = true;
                    sum = -sum * 10 - Integer.parseInt(String.valueOf(c)); //负数相加要用减号
                } else if (i - 1 >= 0
                        && (str.charAt(i - 1) != '+' || str.charAt(i - 1) != '-')
                        && !Character.isDigit(str.charAt(i - 1))) { //如果前面即不是"+" 也不是"-" 还不是数字，则返回0
                    return 0;
                } else {
                    if (sum < 0 || negative == true) { // 对于 -00123 需要加上标志位进行判断
                        if (sum < Integer.MIN_VALUE) {  //判断是否越界
                            return Integer.MIN_VALUE;
                        }
                        sum = sum * 10 - Integer.parseInt(String.valueOf(c));
                    } else {
                        if (sum > Integer.MAX_VALUE) { //判断是否越界
                            return Integer.MAX_VALUE;
                        }
                        sum = sum * 10 + Integer.parseInt(String.valueOf(c));
                    }
                }
            }
        }
        return isOutOfRange(sum);
    }

    public static int isOutOfRange(long sum) {
        if (sum > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (sum < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) sum;
    }

}
