package com.moon.samples.propertyanimator;

import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.moon.samples.R;
import com.moon.samples.dsbridge.BaseActivity;
import com.moon.samples.utils.UDebug;

public class PropertyAnimatorActivity extends BaseActivity {

    private Button propertyBtn;
    private CirclePathProgressView view;
    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animator);

        propertyBtn = (Button) findViewById(R.id.propertyBtn);

        propertyBtn.animate().rotationX(10f).translationX(200f).translationY(500f).alpha(0.8f).setDuration(5000);

        view = new CirclePathProgressView(this);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        addContentView(view,params);

        startCircleAnimation();

        propertyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCircleAnimation();
            }
        });

    }

    @Override
    protected String getActionTitle() {
        return "属性动画";
    }

    private void startCircleAnimation(){

        if (objectAnimator == null){
            objectAnimator = ObjectAnimator.ofFloat(view,"progressDepth",0f,180f);
            objectAnimator.setDuration(3000);
        }

        if ( objectAnimator.isStarted()){
            if (!objectAnimator.isPaused()){
                objectAnimator.pause();
            }else {
                objectAnimator.resume();
            }
        }else {
            objectAnimator.start();
        }

    }

}
