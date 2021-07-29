package com.kidd.demos.java.lang;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

/**
 * @author Kidd
 *         CreateTime 2017/9/2.
 */
@Log4j2
public class ThreadTest {

    @Test
    public void testSleepWithInterrupt() throws Exception{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                log.info("t1 sleep start");
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    log.info("t1 interrupted");
                    log.info("t1 sleep end, time: " + (System.currentTimeMillis() - start));
                    return;
                }
                log.info("t1 sleep end, time: " + (System.currentTimeMillis() - start));
            }
        });

        t1.start();
        Thread.sleep(3000);
        t1.interrupt();
        t1.join();
        log.info("main thread end");
    }
}
