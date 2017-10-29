package com.moon.samples.dispatch_event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.moon.samples.BaseActivity;
import com.moon.samples.R;
import com.moon.samples.utils.UDebug;

public class TestDispatchTouchEventActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dispatch_touch_event);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UDebug.i("button click ...");

            }
        });


        findViewById(R.id.button1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        UDebug.i( "CustomButton-onTouch-ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        UDebug.i( "CustomButton-onTouch-ACTION_UP");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected String getActionTitle() {
        return "android事件分发机制";
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                UDebug.i( "MainActivity-dispatchTouchEvent-ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                UDebug.i(  "MainActivity-dispatchTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                UDebug.i( "MainActivity-onTouchEvent-ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                UDebug.i( "MainActivity-onTouchEvent-ACTION_UP");
                break;
            default:
                break;
        }
//        return super.onTouchEvent(event);

        return true;
    }
}
