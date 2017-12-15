package com.moon.samples.bindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;

/**
 * Created by moon on 2017/12/13.
 *
 *   只是不普通的 bindservice 是不能跨进程滴 ！！！
 */

public class BindServiceActivity extends BaseActivity {

    private MyBindedService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MyBindedService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected String getActionTitle() {
        return "简单示例bindservice";
    }


    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyBindedService.MyBinder) service).getService();
            System.out.println("Service连接成功");
            // 执行Service内部自己的方法
            myService.excute();
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    };
}
