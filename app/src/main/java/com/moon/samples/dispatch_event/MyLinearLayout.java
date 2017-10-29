package com.moon.samples.dispatch_event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.moon.samples.utils.UDebug;

/**
 * author: moon
 * created on: 17/10/28 下午11:00
 * description:
 */
public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    // 以下为重点方法


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                UDebug.i( "CustomLayout-dispatchTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                UDebug.i("CustomLayout-dispatchTouchEvent-ACTION_UP");
                break;

            default:
                break;

        }
//        return super.dispatchTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                UDebug.i( "CustomLayout-onInterceptTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                UDebug.i( "CustomLayout-onInterceptTouchEvent-ACTION_UP");
                break;

            default:
                break;

        }
        return super.onInterceptTouchEvent(ev);
//        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                UDebug.i( "CustomLayout-onTouchEvent-ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                UDebug.i("CustomLayout-onTouchEvent-ACTION_UP");
                break;
            default:
                break;

        }
        return super.onTouchEvent(event);
//        return false;
    }



}
