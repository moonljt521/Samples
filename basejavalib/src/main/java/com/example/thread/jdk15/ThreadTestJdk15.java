package com.example.thread.jdk15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  实现生产者消费的案例， 使用 JDK1.5 新增的若干线程工具类
 */
public class ThreadTestJdk15 {

    public static void main(String[] args) {

        Resource resource = new Resource();

        new Thread(new Producer(resource)).start();
        new Thread(new Producer(resource)).start();
        new Thread(new Consumer(resource)).start();
        new Thread(new Consumer(resource)).start();

    }
}


class Resource {

    // jdk 1.5 线程工具类
    private Lock lock = new ReentrantLock();

    private Condition produCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();


    private int apple;

    private boolean hasPlant;

    public void createApple() {

        lock.lock();

        try {
            while (hasPlant) {
                produCondition.await();
            }


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            apple++;

            hasPlant = true;

            System.out.println(Thread.currentThread().getName() + "种苹果 >>>>>>>>>>>>>>>>>> ： " + apple);

            consumerCondition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void eatApple() {
        lock.lock();

        try {

            while (!hasPlant) {

                consumerCondition.await();
            }

            System.out.println(Thread.currentThread().getName() + "吃苹果 <<<<<<<<<： " + apple);

            hasPlant = false;
            produCondition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


class Producer implements Runnable {

    private Resource r;

    public Producer(Resource resource) {
        this.r = resource;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            r.createApple();

        }

    }
}


class Consumer implements Runnable {

    private Resource r;

    public Consumer(Resource resource) {
        this.r = resource;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            r.eatApple();
        }

    }
}


