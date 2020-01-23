package com.fh.util;

public class Animal {
    public Animal() {
        System.out.println("这是父类的构造函数");
    }

    static{
        System.out.println("这是父类的静态方法");
    }
    {
        System.out.println("这是父类的方法块");
    }
}
