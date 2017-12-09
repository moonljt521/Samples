package com.moon.samples.utils;

import android.util.Log;

/**
 * author: moon
 * created on: 17/9/6 下午3:48
 * description:
 */
public class Logger {
    private static final String TAG = "moon";

    public static void i(String value) {
        if (Constant.DEBUG) {
            Log.e(TAG, value);
        }
    }

    public static void i(String TAG,String value) {
        if (Constant.DEBUG) {
            Log.e(TAG, value);
        }
    }


    public static void i(Object value){
        if (Constant.DEBUG) {
            Log.e(TAG, value+"");
        }
    }


    public static void e(String tag, String value) {
        if (Constant.DEBUG) {
            Log.e(tag, value);
        }
    }

    public static void d(String value) {
        if (Constant.DEBUG) {
            Log.d(TAG, value);
        }

    }

    public static void v(String value) {
        if (Constant.DEBUG) {

            Log.v(TAG, value);
        }

    }

    public static void e(String value) {
        if (Constant.DEBUG) {

            Log.e(TAG, value);
        }
    }
}
