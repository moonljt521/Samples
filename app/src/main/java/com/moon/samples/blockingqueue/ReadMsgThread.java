package com.moon.samples.blockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by moon on 2017/12/24.
 */

public class ReadMsgThread implements Runnable {

    private BlockingQueue<String> queue;

    private boolean isStop = true;

    private int size;

    private int random;

    private List<String> msgList;

    public void setMsg(IReadMsg msg) {
        this.msgListener = msg;
    }

    private IReadMsg msgListener;

    public ReadMsgThread(BlockingQueue<String> queue){
        this.queue = queue;
        msgList = new ArrayList<>();
    }

    @Override
    public void run() {
        while (isStop){
            try {
//                String msg = queue.poll(0,TimeUnit.SECONDS);
                String msg = queue.take();
                if (msg != null){
                    msgList.add(msg);
                }else {
                    System.out.println("读取失败"+ random +"次");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " mlist size = " + msgList.size());

            if (msgList.size() == 100){

                if (msgListener!=null){
                    msgListener.getMsg(msgList);
                }

                msgList.clear();

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public void quitRedMsgQueue(){
        isStop = false;
    }

    interface IReadMsg{

        void getMsg(List<String> list);

    }

}
