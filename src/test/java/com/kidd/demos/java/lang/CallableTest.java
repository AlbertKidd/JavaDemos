package com.kidd.demos.java.lang;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Callable;

/**
 * @author Kidd
 */
@Log4j2
public class CallableTest {

    public static Callable<String> CALLABLE_STRING = () -> "CALLABLE_STRING";
    public static Callable<String> CALLABLE_EXCEPTION = () -> {
        throw new RuntimeException("CALLABLE_EXCEPTION");
    };
}
