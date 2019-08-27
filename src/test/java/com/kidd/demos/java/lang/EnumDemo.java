package com.kidd.demos.java.lang;

/**
 * @author Kidd
 */
public class EnumDemo {

    public static void main(String[] args) {
        MyEnum.valueOf("kidd");
    }

    enum MyEnum{
        KIDD, WANWAN
    }
}
