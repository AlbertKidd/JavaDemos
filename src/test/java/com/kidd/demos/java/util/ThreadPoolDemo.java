package com.kidd.demos.java.util;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.*;

/**
 * @author Kidd
 * CreateTime 2018/1/3.
 */
@Log4j2
public class ThreadPoolDemo {

    private Task task;

    @BeforeTest
    public void setup(){
        task = new Task();
    }

    @Test
    public void cachedThreadPool() throws Exception{
        ExecutorService threadPool = Executors.newCachedThreadPool();
        runTask(threadPool);
    }

    @Test
    public void fixedThreadPool() throws Exception{
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        runTask(threadPool);
    }

    @Test
    public void scheduledThreadPool() throws Exception{
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        log.info("---- start ----");
        threadPool.schedule(task, 500, TimeUnit.MILLISECONDS);
        Thread.sleep(1000);
        ScheduledFuture<?> schedule = threadPool.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);
        schedule.cancel(false);
        Thread.sleep(10000);
    }

    @Test
    public void singleThread() throws Exception{
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        runTask(threadPool);
    }

    @Test
    public void threadPoolExecutor() throws Exception{
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024));
        runTask(threadPool);
    }

    private void runTask(ExecutorService threadPool) throws Exception{
        for (int i = 0; i < 10; i++){
            task.setMsg(i + "");
            Thread.sleep(500);
            threadPool.execute(task);
        }
        threadPool.shutdown();
    }
}
