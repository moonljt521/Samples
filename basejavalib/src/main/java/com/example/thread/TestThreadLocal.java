package com.example.thread;

/**
 * author: moon
 * created on: 17/12/11 下午5:11
 * description:  理解下 ThreadLocal
 *          当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
 *          所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
 *
 */
public class TestThreadLocal {

    public static void main(String[] args) {

        TestThreadLocal testThreadLocal = new TestThreadLocal();

        testThreadLocal.execute(testThreadLocal);

    }

    private void execute(TestThreadLocal testThreadLocal){
        Thread t1 = new Thread(new Runnable1(this));
        t1.start();

        Thread t2 = new Thread(new Runnable2(this));
        t2.start();

    }

    private int getValue(){
//        num.set(num.get() + 1);
//        return num.get();
        num ++ ;
        return num;

    }


    private int num = 0;

//    private ThreadLocal<Integer> num = new ThreadLocal<Integer>(){
//
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };

    private class Runnable1 implements Runnable {

        private TestThreadLocal s ;

        public Runnable1(TestThreadLocal s) {
            this.s = s;
        }

        @Override
        public void run() {
            for (int i =0;i < 10;i++){
                System.out.println(Thread.currentThread().getName() + " -->" + s.getValue());
            }
        }
    }

    private class Runnable2 implements Runnable {
        private TestThreadLocal s ;

        Runnable2(TestThreadLocal s) {
            this.s = s;
        }

        @Override
        public void run() {
            for (int i =0;i < 10;i++){
                System.out.println(Thread.currentThread().getName() + " -->" + s.getValue());
            }
        }
    }

}
