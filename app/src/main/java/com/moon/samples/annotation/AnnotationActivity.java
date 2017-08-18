package com.moon.samples.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.annotation.seriable.GeneratedClass;
import com.moon.samples.R;

public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        new TestCar();

        Toast.makeText(this, new GeneratedClass().getValue() ,Toast.LENGTH_LONG).show();


    }
}
