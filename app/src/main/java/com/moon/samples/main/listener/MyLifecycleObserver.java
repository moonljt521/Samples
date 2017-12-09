package com.moon.samples.main.listener;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.moon.samples.utils.Logger;

/**
 * author: moon
 * created on: 17/11/18 下午1:20
 * description:
 */
public class MyLifecycleObserver implements LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){
        Logger.i("life..onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Logger.i("life..onDestroy");

    }

}
