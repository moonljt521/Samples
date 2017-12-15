package com.moon.samples.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by moon on 2017/12/13.
 */

public class MyBindedService extends Service {

    private final IBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyBinder extends Binder {
        MyBindedService getService() {
            return MyBindedService.this;
        }
    }

    public void excute() {
        System.out.println("通过Binder得到Service的引用来调用Service内部的方法");
    }

    @Override
    public void onDestroy() {
        // 当调用者退出(即使没有调用unbindService)或者主动停止服务时会调用
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // 当调用者退出(即使没有调用unbindService)或者主动停止服务时会调用
        System.out.println("调用者退出了");
        return super.onUnbind(intent);
    }
}
