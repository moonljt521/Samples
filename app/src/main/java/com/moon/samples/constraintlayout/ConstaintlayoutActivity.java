package com.moon.samples.constraintlayout;

import android.os.Bundle;

import com.moon.samples.BaseActivity;
import com.moon.samples.R;

public class ConstaintlayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constaintlayout);
    }

    @Override
    protected String getActionTitle() {
        return "测试 constraint - layout";
    }
}
