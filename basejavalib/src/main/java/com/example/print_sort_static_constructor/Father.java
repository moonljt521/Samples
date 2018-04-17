package com.example.print_sort_static_constructor;

public class Father {

    static {
        System.out.println("Father static block");
    }

    {
        System.out.println("Father constructor block");
    }

    public Father(){
        System.out.println("Father 构造方法");
    }

}
