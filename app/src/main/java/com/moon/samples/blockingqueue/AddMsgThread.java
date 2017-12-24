package com.moon.samples.blockingqueue;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by moon on 2017/12/24.
 */

public class AddMsgThread implements Runnable {

    private BlockingQueue<String> queue;

    private boolean isStop = true;

    private long size;



    public AddMsgThread(BlockingQueue<String> queue) {
        this.queue = queue;

    }

    @Override
    public void run() {
        while (isStop) {

            try {
               size++;

                queue.put("" + size);

                if (size%100 == 0){
                    Thread.sleep(200);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void quitAddMsgQueue() {
        isStop = false;
    }

}
