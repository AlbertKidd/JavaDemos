package com.kidd.demos.java.util.concurrent;

import com.kidd.demos.java.lang.RunnableTest;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Kidd
 */
@Log4j2
public class ExecutorsTest {

    public static ExecutorService SINGLE_THREAD_EXECUTOR = Executors.newSingleThreadExecutor(ThreadFactoryTest.THREAD_FACTORY);

    @Test
    public void testCachedThreadPool() throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool(ThreadFactoryTest.THREAD_FACTORY);
        executorService.submit(RunnableTest.RUNNABLE_PRINT);
        Future<?> future = executorService.submit(RunnableTest.RUNNABLE_EXCEPTION);
        try {
            log.info(future.get());
        }catch (Exception e){
            log.error(e);
        }
        executorService.execute(RunnableTest.RUNNABLE_PRINT);
        executorService.execute(RunnableTest.RUNNABLE_EXCEPTION);
    }
}
