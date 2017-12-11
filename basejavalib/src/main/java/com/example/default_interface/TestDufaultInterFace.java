package com.example.default_interface;

/**
 * author: moon
 * created on: 17/12/11 下午5:40
 * description:  java 8 的接口里的方法 如果加了default 修饰，可以写方法主题了 ！！！
 */
public class TestDufaultInterFace implements IA ,IB{

    public static void main(String[] args) {
        new TestDufaultInterFace().print1();
        new TestDufaultInterFace().print();
    }

//    @Override
//    public void print() {
//        System.out.println("impl .. print ..");
//    }

    @Override
    public void print1() {
        System.out.println("impl .. print1 ..");

    }



}
interface IA {
    public void print1();

}


interface IB {
    default void print(){
        System.out.println("print.. default..");
    }

}