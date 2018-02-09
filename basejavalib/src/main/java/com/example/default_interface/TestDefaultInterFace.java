package com.example.default_interface;

/**
 * author: moon
 * created on: 17/12/11 下午5:40
 * description:  java 8 的接口里的方法 如果加了default 修饰，可以写方法主体了 ！！！
 */
public class TestDefaultInterFace implements IA ,IB{

    public static void main(String[] args) {

        TestDefaultInterFace defaultInterFace = new TestDefaultInterFace();

        int x =  defaultInterFace.getValue();
        System.out.println("x = "+x);

        defaultInterFace.getA();

    }

    @Override
    public void print1() {
        System.out.println("impl .. print1 ..");

    }

    @Override
    public void getA() {
        System.out.println(123);
    }


}
interface IA {
    public void print1();

//    void getA();

}


interface IB {

    void getA();


    default int getValue(){
        return 11;
    }

    default void print(){
        System.out.println("print.. default..");
    }

}