package com.moon.samples.handler_thread;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;
import com.moon.samples.utils.Logger;

public class HandlerThreadActivity extends BaseActivity {

    Handler handler ;
    private HandlerThread handlerThread;
    private int i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        handlerThread = new HandlerThread("myHandlerThread");
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                try {
                    Thread.sleep(2000);
                    Logger.i("curr" + Thread.currentThread().getName());
                    Logger.i("i = " + i);
                    i ++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return true;
            }
        }){

            @Override
            public void handleMessage(Message msg) {

                try {
                    Thread.sleep(2000);
                    Logger.i(">> = " + i);
                    i ++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };


        handler.sendEmptyMessage(101);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected String getActionTitle() {
        return "测试handlerThread";
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onDestroy() {
        super.onDestroy();

        handlerThread.quitSafely();
    }
}
