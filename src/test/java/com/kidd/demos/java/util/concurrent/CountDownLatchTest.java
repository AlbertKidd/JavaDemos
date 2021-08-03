package com.kidd.demos.java.util.concurrent;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Kidd
 */
@Log4j2
public class CountDownLatchTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    @Test
    public void countDown() {
        synchronized (this){
            countDownLatch.countDown();
            try {
                Thread.sleep(2);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            log.info("current count: {}", countDownLatch.getCount());
        }
    }

    @Test
    public void await() throws Exception{
        log.info("count down started");
        IntStream.range(0, 10).forEach(i -> new Thread(this::countDown).start());
        countDownLatch.await();
        log.info("count down finished, rest count: {}", countDownLatch.getCount());
    }

    @Test
    public void awaitTime() throws Exception{
        log.info("count down started");
        IntStream.range(0, 10).forEach(i -> new Thread(this::countDown).start());
        boolean finished = countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("count down is finished: {}, rest count: {}", finished, countDownLatch.getCount());
    }


}
