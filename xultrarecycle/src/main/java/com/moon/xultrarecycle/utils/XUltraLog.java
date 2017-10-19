package com.moon.xultrarecycle.utils;

import android.util.Log;

/**
 * author: moon
 * created on: 17/9/6 下午3:48
 * description:
 */
public class XUltraLog {

    private XUltraLog(){}

    private static final String TAG = "moon";

    public static boolean DEBUG = true;

    public static void i(Object value){
        if (DEBUG) {
            Log.e(TAG, value+"");
        }
    }

    public static void i(String TAG,String value) {
        if (DEBUG) {
            Log.e(TAG, value);
        }
    }

    public static void e(String tag, String value) {
        if (DEBUG) {
            Log.e(tag, value);
        }
    }

    public static void d(String value) {
        if (DEBUG) {
            Log.d(TAG, value);
        }

    }

    public static void v(String value) {
        if (DEBUG) {

            Log.v(TAG, value);
        }

    }

    public static void e(String value) {
        if (DEBUG) {

            Log.e(TAG, value);
        }
    }
}
