package com.kidd.demos.java.lang;

import lombok.extern.log4j.Log4j2;

/**
 * @author Kidd
 */
@Log4j2
public class RunnableTest {

    public static Runnable RUNNABLE_PRINT = () -> log.info("RUNNABLE_PRINT");
    public static Runnable RUNNABLE_EXCEPTION = () -> {
        throw new RuntimeException("RUNNABLE_EXCEPTION");
    };
}
