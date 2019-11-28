package com.kidd.demos.java.util.cocurrent;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutor的测试
 * @author Kidd
 */
@Log4j2
public class ScheduledExecutorTest {

    private ScheduledExecutorService scheduledExecutorService;

    @BeforeTest()
    public void init() throws Exception {
        scheduledExecutorService = Executors.newScheduledThreadPool(50);
        Thread.sleep(5000);
    }

    @Test
    public void testScheduleAtFixRate() throws Exception {
        scheduledExecutorService.scheduleAtFixedRate(getTask(), getCeilingMinuteTime(), 500, TimeUnit.MILLISECONDS);
        Thread.sleep(5000);
    }

    @Test
    public void testScheduleAtFixDelay() throws Exception {
        scheduledExecutorService.scheduleWithFixedDelay(getTask(), getCeilingMinuteTime(), 500, TimeUnit.MILLISECONDS);
        Thread.sleep(5000);
    }

    private Runnable getTask() {
        return new Runnable() {
            @Override
            public void run() {
                log.info("任务开始");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        };
    }

    private long getCeilingMinuteTime() {
        Date ceilingDate = DateUtils.ceiling(new Date(), Calendar.SECOND);
        return ceilingDate.getTime() - System.currentTimeMillis();

    }

    private long test() {
        Date date = DateUtils.setDays(new Date(), 15);
        long specifiedTime = date.getTime();
        if (specifiedTime < System.currentTimeMillis()) {
            specifiedTime = DateUtils.addMonths(date, 1).getTime();
        }
        return specifiedTime - System.currentTimeMillis();
    }
}
