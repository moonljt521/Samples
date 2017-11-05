package com.moon.xultrarecycle.utils;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by lichuang on 16/11/15.
 */
public class XRecyclerViewUtils {
    private XRecyclerViewUtils(){

    }

    //判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static void removeParentView(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup pView = (ViewGroup) parent;
                pView.removeView(view);
            }
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
