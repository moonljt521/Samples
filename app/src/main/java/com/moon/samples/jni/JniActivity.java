package com.moon.samples.jni;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moon.samples.base.BaseActivity;
import com.moon.samples.R;
import com.moon.samples.jni.util.JniUtil;

public class JniActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);


        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"获取c++中的字符串为"+JniUtil.getValue(),Toast.LENGTH_LONG).show();

            }
        });



        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JniUtil.callNativeAndJniCallJava("callNativeAndJniCallJava...params..");

            }
        });


        findViewById(R.id.bt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"向C++中传递字符串1，经过+1操作后返回"+JniUtil.callNativeAndCallBack(1),Toast
                        .LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected String getActionTitle() {
        return "JNI和Java互调";
    }
}
