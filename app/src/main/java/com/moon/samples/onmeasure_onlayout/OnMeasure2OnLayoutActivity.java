package com.moon.samples.onmeasure_onlayout;

import android.os.Bundle;

import com.moon.samples.R;
import com.moon.samples.BaseActivity;

public class OnMeasure2OnLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onmeasure_onlayout);


    }

    @Override
    protected String getActionTitle() {
        return "onmeasure_onlayout";
    }
}
