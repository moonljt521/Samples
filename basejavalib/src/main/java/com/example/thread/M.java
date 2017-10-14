package com.example.thread;

import java.net.DatagramPacket;
import java.util.concurrent.TimeUnit;

/**
 * author: moon
 * created on: 17/8/24 下午12:31
 * description:
 */
public class M extends Father{



    private boolean isStop;
    private static boolean k;
    private static int i = 0;
    public static void main(String[] args) {

        M m = new M();
        m.sync1();
        m.stopSync();

        m.abc();
        System.out.println(m.a);

//        k = false;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (!k){
//                    i++;
//                    System.out.println(">>"+i);
//                }
//
//
//            }
//        }).start();
//
//        try {
//            TimeUnit.SECONDS.sleep(3);
//            k = true;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private void sync1(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                int i = 0;

                while (!isStop){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    i ++ ;
                    System.out.println(i);

                    if (i == 50){
//                        isStop = true;
                    }

                }

            }
        }).start();

    }


    private void stopSync(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (this){

                    try {
                        TimeUnit.SECONDS.sleep(3);

                        isStop = true;

                        System.out.println(Thread.currentThread().getName()+ ":"+ isStop);

//                        notifyAll();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    public void read() {
        super.read();
    }

}

 class Father {

     static String a = "2";

    public void read(){
        System.out.println("father read ...");
    }

    static void abc(){
        System.out.println("father abc ...");
    }

}
