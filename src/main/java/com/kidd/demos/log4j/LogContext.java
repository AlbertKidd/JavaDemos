package com.kidd.demos.log4j;

/**
 * @author Kidd
 */
public class LogContext {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String get(){
        return threadLocal.get();
    }

    public static void set(String value){
        threadLocal.set(value);
    }
}
