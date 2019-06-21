package com.kidd.demos.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * @author Kidd
 */
@Configuration
public class ThreadBeans {

    @Bean
    public ThreadPoolExecutorFactoryBean threadPoolExecutor(){
        return new ThreadPoolExecutorFactoryBean();
    }

    @Bean
    public ThreadFactory threadFactory(){
        CustomizableThreadFactory threadFactory = new CustomizableThreadFactory("kidd");
        threadFactory.setThreadGroupName("kiddGroup");
        return threadFactory;
    }

    @Bean
    public TestBean testBean(){
        return new TestBean();
    }

}
