package com.example.singleton;

/**
 * author: moon
 * created on: 17/9/15 上午11:58
 * description:
 */
public class SingleTon {


    public static void main(String[] args) {

        for (int i=0;i<20;i ++){

            new Thread(new Runnable() {
                @Override
                public void run() {

//                    A a = A.getA();
//                    a.setX(10);
//                    System.out.println(a);
//                    System.out.println("A:"+a.getX());

                    B b = B.getBInstance();
                    System.out.println("a="+b);


//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                }
            }).start();

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
////                    A a = A.getA();
////                    a.setX(11);
////                    System.out.println(a);
////                    System.out.println("B:"+a.getX());
//
//                    B b = B.getBInstance();
//                    System.out.println("b="+b);
//
//                }
//            }).start();
        }




    }

}

class B {

    private static B b ;

    private B (){

    }

    public static B getBInstance(){
        if (b == null){
            b = new B();
        }
        return b;
    }

}


class A {

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static void setA(A a) {
        A.a = a;
    }

    private volatile int x = 9;

    private A (){

    }

    private static volatile A a;

    public static A getA() {
        if (a == null){
            synchronized (A.class){
                if (a == null){
                    a = new A();
                }
            }
        }
        return a;
    }


    private Object readResolve(){
        return a;
    }


}


enum Mys {

    INSTANCE;

    private int x = 0;

    public void setX(){
        this.x = 10;
    }

    public void getX(){
        System.out.println("x="+x);
    }

}

