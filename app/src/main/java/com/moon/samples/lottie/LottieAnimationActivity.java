package com.moon.samples.lottie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.moon.samples.R;
import com.moon.samples.dsbridge.BaseActivity;

public class LottieAnimationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_animation);
    }

    @Override
    protected String getActionTitle() {
        return "testLottieAnimation";
    }
}
