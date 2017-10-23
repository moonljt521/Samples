package com.moon.samples.propertyanimator;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.moon.samples.R;
import com.moon.samples.BaseActivity;

public class PropertyAnimatorActivity extends BaseActivity {

    private Button propertyBtn;
    private CirclePathProgressView view;
    private TestArgbEvaluatorView argbEvaluatorView;
    private ObjectAnimator objectAnimator;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animator);

        propertyBtn = (Button) findViewById(R.id.propertyBtn);

        propertyBtn.animate().rotationX(10f).translationX(200f).translationY(500f).alpha(0.8f).setDuration(5000);

        view = new CirclePathProgressView(this);

        argbEvaluatorView = new TestArgbEvaluatorView(this);

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

        addContentView(argbEvaluatorView,params);

//        ObjectAnimator o1 = ObjectAnimator.ofInt(argbEvaluatorView,"color",0xffff0000,0xff00ff00);
        ObjectAnimator o1 = ObjectAnimator.ofArgb(argbEvaluatorView,"color", Color.BLUE, Color.GREEN);
//        o1.setEvaluator(new ArgbEvaluator());
        o1.setDuration(5000);
        o1.start();


//        Glide.with(this).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZxZcaaglkzRWSK7HeQiqGkDSSjV6qRyzOCaxbstxQmreUC2mxQg")
//                .into(imageView);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
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
