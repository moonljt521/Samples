package com.moon.samples.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lichuang on 16/11/15.
 */
public class UiUtils {


    private UiUtils() {

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


    public static int getViewDpeth(Activity context, int resId) {
        ViewGroup viewGroup = context.findViewById(resId);

        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return 1;
        }

        List<View> list = new LinkedList<>();

        return getViewDepth(list, viewGroup);
    }

    /**
     * 获取view 的深度
     *
     * @return
     */
    private static int getViewDepth(List<View> list, ViewGroup viewGroup) {

        int size = viewGroup.getChildCount();

        for (int i = 0; i < size; i++) {

            View view1 = viewGroup.getChildAt(i);

            if (view1 instanceof ViewGroup) {

                list.add(view1);

                getViewDepth(list, (ViewGroup) view1);
            }
        }
        return list.size();
    }

    public static void traversalView(View view) {
        if (null == view) {
            return;
        }
        LinkedList<ViewGroup> queue = new LinkedList<>();


        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            queue.add(viewGroup);
            while (!queue.isEmpty()) {
                ViewGroup current = queue.removeFirst();
                //dosomething
                for (int i = 0; i < current.getChildCount(); i++) {
                    if (current.getChildAt(i) instanceof ViewGroup) {
                        queue.addLast((ViewGroup) current.getChildAt(i));
                    } else {
                        //dosomething
                    }
                }
            }
        } else {
            //dosomething
        }

        Logger.i("size" + queue.size());
    }

}
