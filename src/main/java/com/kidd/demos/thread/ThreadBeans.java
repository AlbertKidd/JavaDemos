package com.kidd.demos.thread;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kidd
 */
@Configuration
@Log4j2
public class ThreadBeans {

    private static final String THREAD_GROUP_NAME = "GROUP";
    private static final String THREAD_PREFIX_NAME = "PREFIX";
    private static final int THEAD_POOL_CORE_SIZE = 4;

    @Bean
    public ThreadPoolExecutorFactoryBean executor() {
        ThreadPoolExecutorFactoryBean factoryBean = new ThreadPoolExecutorFactoryBean();
        factoryBean.setThreadGroupName(THREAD_GROUP_NAME);
        factoryBean.setThreadNamePrefix(THREAD_PREFIX_NAME);
        factoryBean.setCorePoolSize(THEAD_POOL_CORE_SIZE);
        return factoryBean;
    }

    @Bean
    public ScheduledExecutorFactoryBean scheduledExecutor() {
        ScheduledExecutorFactoryBean factoryBean = new ScheduledExecutorFactoryBean();
        factoryBean.setThreadGroupName(THREAD_GROUP_NAME);
        factoryBean.setThreadNamePrefix(THREAD_PREFIX_NAME);
        factoryBean.setPoolSize(THEAD_POOL_CORE_SIZE);
        factoryBean.setScheduledExecutorTasks(scheduledExecutorTask());
        factoryBean.setContinueScheduledExecutionAfterException(true);
        return factoryBean;
    }

    public ScheduledExecutorTask scheduledExecutorTask() {
        AtomicInteger times = new AtomicInteger();
        ScheduledExecutorTask scheduledExecutorTask = new ScheduledExecutorTask();
        scheduledExecutorTask.setRunnable(new Runnable() {
            @Override
            public void run() {
                log.info("executed {} times", times.incrementAndGet());
                if (times.intValue() == 10) {
                    throw new RuntimeException("should teminate?");
                }
            }
        });
        scheduledExecutorTask.setPeriod(100);
        scheduledExecutorTask.setTimeUnit(TimeUnit.MILLISECONDS);
        return scheduledExecutorTask;
    }

    @Bean
    public ThreadFactory threadFactory() {
        CustomizableThreadFactory threadFactory = new CustomizableThreadFactory("kidd");
        threadFactory.setThreadGroupName(THREAD_GROUP_NAME);
        threadFactory.setThreadNamePrefix(THREAD_PREFIX_NAME);
        return threadFactory;
    }


}
