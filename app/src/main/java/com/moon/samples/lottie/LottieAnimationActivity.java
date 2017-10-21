package com.moon.samples.lottie;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.moon.samples.R;
import com.moon.samples.BaseActivity;

public class LottieAnimationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_animation);

        Glide.with(this).load("").into(new ImageView(this));
    }

    @Override
    protected String getActionTitle() {
        return "testLottieAnimation";
    }
}
