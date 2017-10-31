package com.moon.samples.dispatch_event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.moon.samples.utils.UDebug;

/**
 * author: moon
 * created on: 17/10/28 下午11:02
 * description:
 */
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //以下为重点方法

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                UDebug.i( "button - onTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                UDebug.i("button - onTouchEvent-ACTION_UP");
                break;

            default:
                break;

        }

        return super.onTouchEvent(ev);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                UDebug.i( "button -dispatchTouchEvent-ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP:
                UDebug.i("button -dispatchTouchEvent-ACTION_UP");
                break;

            default:
                break;

        }

        return super.dispatchTouchEvent(ev);
    }
}
