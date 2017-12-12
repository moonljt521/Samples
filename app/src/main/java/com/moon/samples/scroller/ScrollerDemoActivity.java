package com.moon.samples.scroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;

public class ScrollerDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_demo);
    }

    @Override
    protected String getActionTitle() {
        return "用Scroller写个Viewpager";
    }
}
