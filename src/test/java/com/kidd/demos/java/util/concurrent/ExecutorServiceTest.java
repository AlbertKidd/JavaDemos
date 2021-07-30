package com.kidd.demos.java.util.concurrent;

import com.kidd.demos.java.lang.CallableTest;
import com.kidd.demos.java.lang.RunnableTest;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.kidd.demos.java.util.concurrent.ExecutorsTest.SINGLE_THREAD_EXECUTOR;

/**
 * @author Kidd
 */
@Log4j2
public class ExecutorServiceTest {

    @Test
    public void testRunnableSubmit() throws Exception{
        Future<?> emptyFuture = SINGLE_THREAD_EXECUTOR.submit(RunnableTest.RUNNABLE_PRINT);
        log.info("emptyFuture: {}", emptyFuture.get());
        Future<?> exceptionFuture = SINGLE_THREAD_EXECUTOR.submit(RunnableTest.RUNNABLE_EXCEPTION);
        try {
            exceptionFuture.get();
        }catch (Exception e){
            log.info("exceptionFuture: {}", e.getMessage());
        }
    }

    @Test
    public void testCallableSubmit() throws Exception{
        Future<String> stringFuture = SINGLE_THREAD_EXECUTOR.submit(CallableTest.CALLABLE_STRING);
        log.info("stringFuture: {}", stringFuture.get());
        Future<String> exceptionFuture = SINGLE_THREAD_EXECUTOR.submit(CallableTest.CALLABLE_EXCEPTION);
        try {
            exceptionFuture.get();
        }catch (Exception e){
            log.info("exceptionFuture: {}", e.getMessage());
        }
    }
}
