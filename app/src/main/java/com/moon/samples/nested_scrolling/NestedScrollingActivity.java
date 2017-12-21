package com.moon.samples.nested_scrolling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;

public class NestedScrollingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrolling);
    }

    @Override
    protected String getActionTitle() {
        return "学习下嵌套滚动";
    }
}
