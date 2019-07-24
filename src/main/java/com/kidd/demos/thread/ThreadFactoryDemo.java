package com.kidd.demos.thread;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Kidd
 */
@Log4j2
public class ThreadFactoryDemo {

    public static void main(String[] args) throws Exception{

        ApplicationContext context = new AnnotationConfigApplicationContext(ThreadBeans.class);
        ThreadPoolExecutor threadPoolExecutor = context.getBean("threadPoolExecutor", ThreadPoolExecutor.class);
        ThreadFactory threadFactory = context.getBean("threadFactory", ThreadFactory.class);
        threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                log.info("hello");
            }
        }).start();

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                log.info("hello");
            }
        });
    }
}
