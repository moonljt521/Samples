package com.moon.samples.messenger_ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.moon.samples.R;
import com.moon.samples.base.BaseActivity;
import com.moon.samples.messenger_ipc.service.MessengerService;
import com.moon.samples.utils.ExplictFromIntent;
import com.moon.samples.utils.Logger;

import xiaofei.library.hermeseventbus.HermesEventBus;

public class MessengerIPCActivity extends BaseActivity implements View.OnClickListener{

    private Button button;

    private TextView textView;

    private TextView stateTv;

    private static final int MSG_SUM = 0x110;

    private Messenger mService;

    private boolean isConn;

    private int intID;

    private MessengerService relService;

    private Messenger messenger = new Messenger(new Handler(){

        @Override
        public void handleMessage(Message msg4Server) {

            switch (msg4Server.what){
                case MSG_SUM:
                    // 来自服务器

                    MessengerIPCBean m = (MessengerIPCBean) msg4Server.getData().getSerializable("serverData");
                    textView.setText("来自Server的信息：" + m.getName());

                    break;
            }

            super.handleMessage(msg4Server);
        }
    });

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            isConn = true;
            stateTv.setText("connected");

//            relService = (MessengerService) service;
            //
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isConn = false;
            stateTv.setText("disConnected");

            relService = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_ipc);

        // TODO: 17/11/28
        button = findViewById(R.id.connect);

        textView = findViewById(R.id.showText);

        stateTv = findViewById(R.id.connectState);

        button.setOnClickListener(this);

        findViewById(R.id.callServiceMethod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (relService != null){
                    relService.testCall();
                }


            }
        });

        //---------
        bindService();

    }

    private void bindService(){
        Intent intent = new Intent();
        intent.setAction("com.moon.samples.test.messenger.ipc");

        Intent oIntent = new Intent(ExplictFromIntent.createExplicitFromImplicitIntent(this,intent));
        bindService(oIntent, mConn, Context.BIND_AUTO_CREATE);
        Logger.i( "bindService invoked !");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(mConn);
        android.os.Process.killProcess(android.os.Process.myPid());// 直接退出
    }

    @Override
    protected String getActionTitle() {
        return "测试 messenger 的进程间通信";
    }

    @Override
    public void onClick(View v) {
        intID ++;

        MessengerIPCBean bean = new MessengerIPCBean();
        bean.setId(intID);
        bean.setName(intID + "ljt");

        Bundle bundle = new Bundle();
        bundle.putSerializable("data",bean);


        Message msgFromClient = Message.obtain(null, MSG_SUM);
        msgFromClient.setData(bundle);

        msgFromClient.replyTo = messenger;
        if (isConn){
            try {
                mService.send(msgFromClient);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        HermesEventBus.getDefault().post("111");
    }


}
