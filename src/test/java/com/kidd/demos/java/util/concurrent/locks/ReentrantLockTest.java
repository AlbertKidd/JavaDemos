package com.kidd.demos.java.util.concurrent.locks;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static com.kidd.demos.java.util.concurrent.ThreadFactoryTest.THREAD_FACTORY;

/**
 * @author Kidd
 */
@Log4j2
public class ReentrantLockTest {

    private static ReentrantLock fairLock = new ReentrantLock(true);

    private static ReentrantLock lock = new ReentrantLock();

    @Test
    public void testFairSync() throws Exception{
        // the thread will print in sequence
        testFair(fairLock);
    }

    @Test
    public void testNonfairSync() throws Exception{
        // the thread will print out of sequence
        testFair(lock);
    }

    public void testFair(ReentrantLock reentrantLock) throws Exception{
        Runnable runnable = () -> {
            reentrantLock.lock();
            log.info("thread finish");
            reentrantLock.unlock();
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            Thread thread = THREAD_FACTORY.newThread(runnable);
            threads.add(thread);
        }

        // ensure forward threads threads blocking
        reentrantLock.lock();

        for (int i = 0; i < threads.size(); i++) {
            Thread thread = threads.get(i);
            thread.start();

            // after started first of these threads, release lock, let the other half acquire lock immediately
            if (i == 5){
                reentrantLock.unlock();
            }
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    public void testInterrupt() throws Exception{
        lock.lock();
        log.info("main thread acquired lock");

        Thread t1 = THREAD_FACTORY.newThread(() -> {
            try {
                log.info("thread started");
                lock.lockInterruptibly();
                log.info("thread locked");
            } catch (InterruptedException e) {
                log.info("thread interrupted");
                return;
            }
            lock.unlock();
            log.info("thread unlocked");
        });
        t1.start();

        Thread.sleep(1000);

        t1.interrupt();
        log.info("thread {} interrupted", t1.getName());

        lock.unlock();
        log.info("main thread release lock");
    }
}
