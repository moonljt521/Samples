package com.example.java8datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;

/**
 * author: moon
 * created on: 18/1/6 上午11:37
 * description: 学习java 8中的时间格式
 */
public class TestJava8Date {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();

        System.out.println(localDate);

        System.out.println("localDate.getYear() = "+localDate.getYear() +
                ",localDate.getYear() = " + localDate.getMonthValue() +
                ",localDate.getDayOfMonth() = " + localDate.getDayOfMonth());


        // 本月 第?天（两种写法）
        System.out.println(localDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(localDate.withDayOfMonth(22));

        // 获取日期是星期几
        System.out.println("星期"+localDate.get(ChronoField.DAY_OF_WEEK));
        System.out.println("星期"+localDate.withDayOfMonth(22).get(ChronoField.DAY_OF_WEEK));

        // 时间，不包含日期
        LocalTime nowTime = LocalTime.now(); //结果  14:50:18.933  默认会显示毫秒
        System.out.println("当前的时间=" + nowTime);
        System.out.println("当前的时间=" + nowTime.withNano(0)); // 去掉毫秒： 结果 14:51:38

        // 格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY~MM~dd");
        System.out.println("格式化后的日期 = "+formatter.format(localDate));

        formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        System.out.println("格式化后的日期 = "+formatter.format(localDate));

        // 时间戳
        Instant instant = Instant.now();
        System.out.println("时间戳 = "+instant);
        System.out.println("时间戳java7(毫秒) = "+System.currentTimeMillis());
        System.out.println("时间戳java8(毫秒) = "+instant.toEpochMilli());
        System.out.println("时间戳java8(零秒) = "+instant.getEpochSecond());
        System.out.println("时间戳java8(纳秒) = "+instant.getNano());


        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = "+localDateTime);
        System.out.println("localDateTime转换时间戳 = "+localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli
                ());



    }

    public static int getDayofWeek(){
        return LocalDate.now().get(ChronoField.DAY_OF_WEEK);
    }

}

