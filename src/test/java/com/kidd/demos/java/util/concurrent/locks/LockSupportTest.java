package com.kidd.demos.java.util.concurrent.locks;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kidd
 */
@Log4j2
public class LockSupportTest {

    @Test
    public void testPark() throws Exception{
        Thread t1 = new Thread(() -> {
            log.info("t1 start");
            log.info("t1 park");
            LockSupport.park();
            log.info("t1 unpark");
        });

        t1.start();
        log.info("main thread sleep 1500ms");
        Thread.sleep(1500);
        log.info("unpark t1");
        LockSupport.unpark(t1);
        t1.join();
    }

    /**
     * park in lock
     * to verify that park dose not release the lock
     * @throws Exception
     */
    @Test
    public void testParkWithLock() throws Exception{
        Object lockObj = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lockObj){
                log.info("t1 start");
                log.info("t1 park");
                LockSupport.park();
                log.info("t1 unpark");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockObj){
                log.info("t2 start");
            }
        });

        t1.start();
        log.info("main thread sleep 100ms");
        t2.start();
        Thread.sleep(100);
        log.info("main thread sleep 1500ms");
        Thread.sleep(1500);
        log.info("unpark t1");
        LockSupport.unpark(t1);
        t1.join();
    }
}
