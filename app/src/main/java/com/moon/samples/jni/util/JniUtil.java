package com.moon.samples.jni.util;

import android.os.Looper;
import android.widget.Toast;

import com.moon.samples.MyApplication;
import com.moon.samples.utils.Logger;

/**
 * author: moon
 * created on: 17/10/23 上午10:15
 * description:
 */
public class JniUtil {

    static {
        System.loadLibrary("encrypt");
    }

    //只是调用jni方法，获取一个值
    public static native String getValue();

    //调用jni方法，并传递参数，然后获取jni方法 + 1的返回值
    public static native int callNativeAndCallBack(int str);

    //
    public static native String callNativeAndJniCallJava(String str);

    public static void getNativeCallNonParam(){
        Logger.i("java - > getNativeCallNonParam" );

        if (Looper.getMainLooper() != Looper.myLooper() ){

            Logger.i("不是主线程。。。。。");
            return;
        }

        Toast.makeText(MyApplication.getMyApp(),"nonParam",Toast.LENGTH_LONG).show();
    }

    public static void getNativeCall(String value){
        Logger.i("java - > getNativeCall" + value);

        if (Looper.getMainLooper() != Looper.myLooper() ){

            Logger.i("不是主线程。。。。。");
            return;
        }

        Toast.makeText(MyApplication.getMyApp(),value,Toast.LENGTH_LONG).show();
    }

}
