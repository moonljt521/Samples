package com.moon.samples.java8datetime;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import com.example.java8datetime.TestJava8Date;
import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;

public class Java8DateTimeActivity extends BaseActivity {


    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java8_date_time);



//        showToast("今天星期"+TestJava8Date.getDayofWeek());

    }

    @Override
    protected String getActionTitle() {
        return "java8新时间格式api的学习";
    }
}
