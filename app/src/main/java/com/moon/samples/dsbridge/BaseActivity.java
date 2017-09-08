package com.moon.samples.dsbridge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.komi.slider.SliderUtils;
import com.moon.samples.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected boolean notLoadSlide ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(getActionTitle());
        }

        if (!notLoadSlide){
            SliderUtils.attachActivity(this, null);
        }
    }

    protected abstract String getActionTitle();

}
