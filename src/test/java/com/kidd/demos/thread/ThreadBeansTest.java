package com.kidd.demos.thread;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.Assert.*;

/**
 * @author Kidd
 */
@Log4j2
public class ThreadBeansTest {

    private ThreadFactory threadFactory;

    private ExecutorService executorService;

    private ScheduledExecutorService scheduledExecutorService;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @BeforeMethod
    public void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ThreadBeans.class);
        threadFactory = applicationContext.getBean("threadFactory", ThreadFactory.class);
        executorService = applicationContext.getBean("executor", ExecutorService.class);
        scheduledExecutorService = applicationContext.getBean("scheduledExecutor", ScheduledExecutorService.class);
    }

    @Test
    public void testThreadFactory() throws Exception{
        Thread.sleep(10 * 1000);
    }

    public void testExecutorService(){

    }

    @Test
    public void testScheduledExecutorService() throws Exception{
    }

}