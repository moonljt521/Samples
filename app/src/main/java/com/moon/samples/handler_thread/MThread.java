package com.moon.samples.handler_thread;

import android.os.Looper;

import com.moon.samples.utils.Logger;

/**
 * author: moon
 * created on: 17/10/28 下午3:24
 * description:
 */
public class MThread extends Thread{

    public Looper looper;//取出该子线程的Looper

    @Override
    public void run() {

        Logger.i("zi : " + Thread.currentThread());

        Looper.prepare();//创建该子线程的Looper
        looper = Looper.myLooper();//取出该子线程的Looper
        Looper.loop();//只要调用了该方法才能不断循环取出消息
    }
}
