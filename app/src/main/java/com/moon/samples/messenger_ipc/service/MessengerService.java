package com.moon.samples.messenger_ipc.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.moon.samples.messenger_ipc.MessengerIPCBean;
import com.moon.samples.utils.Logger;

/**
 * author: moon
 * created on: 17/11/27 下午2:58
 * description:
 */
public class MessengerService extends Service {
    private static final int MSG_SUM = 0x110;

    private static int serserID;

    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msgfromClient)
        {
            switch (msgfromClient.what)
            {
                //msg 客户端传来的消息
                case MSG_SUM:
                    try
                    {
                        //模拟耗时
                        Thread.sleep(2000);

                        Bundle bundle = msgfromClient.getData();

                        if (bundle ==null){
                           return;
                        }

                        MessengerIPCBean bean = (MessengerIPCBean) bundle.getSerializable("data");
                        Logger.i("来自客户端的数据：" + bean.getName());


                        mMessenger = msgfromClient.replyTo;

                        if (mMessenger!=null){
                            Message msgToClient = Message.obtain(msgfromClient);//返回给客户端的消息
                            msgToClient.what = MSG_SUM;

                            serserID ++ ;
                            // 修改下数据然后传回去
                            bean.setName(bean.getName()+"From" + serserID);

                            Bundle bundleToClient = new Bundle();
                            bundleToClient.putSerializable("serverData",bean);


                            msgToClient.setData(bundleToClient);

                            mMessenger.send(msgToClient);
                        }

                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    } catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }

            super.handleMessage(msgfromClient);
        }
    };

    //最好换成HandlerThread的形式
    private static Messenger mMessenger = new Messenger(handler);

    @Override
    public IBinder onBind(Intent intent)
    {
        return mMessenger.getBinder();
    }
}
