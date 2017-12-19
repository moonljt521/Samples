package com.moon.samples.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.moon.samples.MyApplication;

/**
 * author: moon
 * created on: 17/11/15 下午4:29
 * description:
 */
public class SettingUtil {

    private static SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(MyApplication.getMyApp());

    /**
     * 设置夜间模式
     */
    public static void setIsNightMode(boolean flag) {
        setting.edit().putBoolean("switch_nightMode", flag).apply();
    }
}
