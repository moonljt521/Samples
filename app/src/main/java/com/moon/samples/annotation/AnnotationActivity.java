package com.moon.samples.annotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.moon.samples.R;
import com.moon.samples.dsbridge.BaseActivity;

public class AnnotationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

//        new TestCar();


//        Toast.makeText(this, new GeneratedClass().getValue() ,Toast.LENGTH_LONG).show();


    }

    @Override
    protected String getActionTitle() {
        return "注解";
    }
}
