package com.example.thread.jdk14;


/**
 *  实现生产者消费的案例， 使用 object 方法 wait  notify
 */
public class ThreadTestJdk14 {

    public static void main(String[] args) {

        Resource resource = new Resource();

        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();

    }
}


class Resource {

    private int apple;

    private boolean hasPlant;

    public void createApple() {

        synchronized (this) {

            while (hasPlant) {

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            apple++;

            hasPlant = true;

            System.out.println(Thread.currentThread().getName() + "种苹果 >>>>>>>>>>>>>>>>>> ： " + apple);

            notifyAll();
        }

    }

    public void eatApple() {

        synchronized (this) {


            while (!hasPlant) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "吃苹果 <<<<<<<<<： " + apple);

            hasPlant = false;

            notifyAll();

        }


    }
}

/**
 * 生产者
 */
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

/**
 * 消费者
 */
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


