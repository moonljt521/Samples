package com.moon.samples.jni.util;

import android.os.Looper;
import android.widget.Toast;

import com.moon.samples.MyApplication;
import com.moon.samples.utils.Logger;

/**
 * author: moon
 * created on: 17/10/23 下午2:15
 * description:
 */
public class JniCallBack {


    public JniCallBack(){

    }

    public static void getJniCall(String str){
        if (Looper.getMainLooper() != Looper.myLooper() ){

            Logger.i("不是主线程。。。。。");
            return;
        }

        Toast.makeText(MyApplication.getMyApp(),str,Toast.LENGTH_LONG).show();
    }
}
