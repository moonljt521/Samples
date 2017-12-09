package com.example.thread;

/**
 * author: moon
 * created on: 17/12/9 下午8:49
 * description:  join 的使用： 当线程a 执行完成时，才会执行b，保证了两个线程顺序执行， 而当join方法加参数时，
 *              在指定时间内如果a没有执行完毕，也会执行b， 防止 b无限等待，拖垮程序
 */
public class ThreadJoin {

    public static void main(String[] args) {

       new ThreadJoin().test();;

    }



    public void test(){
        Thread a = new Thread(new ThreadA());
        Thread b = new Thread(new ThreadB());

        a.start();

        try {
//            a.join();
            a.join(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        b.start();
    }



    class ThreadA implements Runnable {


        @Override
        public void run() {

            for (int i=0;i<10 ;i++) {
                try {
                    Thread.sleep(500);
                    System.out.println("threadA ..." + i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class ThreadB implements Runnable {

        @Override
        public void run() {
            System.out.println("threadB .....");
        }
    }


}
