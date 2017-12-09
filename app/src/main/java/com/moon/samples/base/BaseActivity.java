package com.moon.samples.base;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.komi.slider.ISlider;
import com.komi.slider.SliderConfig;
import com.komi.slider.SliderUtils;
import com.moon.samples.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected boolean notLoadSlide ;

    private SliderConfig mConfig = new SliderConfig.Builder()
            .secondaryColor(Color.TRANSPARENT)
            .edge(true)
            .build();
    protected ISlider iSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle(getActionTitle());
        }

        if (!notLoadSlide){
            SliderUtils.attachActivity(this, mConfig);
        }
    }

    protected abstract String getActionTitle();

}
