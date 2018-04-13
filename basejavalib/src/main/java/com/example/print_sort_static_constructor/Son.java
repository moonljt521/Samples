package com.example.print_sort_static_constructor;

public class Son extends Father {

    static {
        System.out.println("Son static block");
    }

    {
        System.out.println("Son constructor block");
    }

    public Son(){
        System.out.println("Son 构造方法");
    }

}
