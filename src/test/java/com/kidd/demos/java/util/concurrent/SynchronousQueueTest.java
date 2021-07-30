package com.kidd.demos.java.util.concurrent;

import org.testng.annotations.Test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kidd
 */
public class SynchronousQueueTest {

    private AtomicInteger atomicInteger = new AtomicInteger();

    private SynchronousQueue<Integer> queue = new SynchronousQueue<>();

    private void put(){
        for (int i = 0; i < 10; i++){
            try {
                queue.put(atomicInteger.getAndIncrement());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void take(){
        for (int i = 0; i < 10; i++){
            try {
                Integer poll = queue.take();
                System.out.println(poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test(){
        Thread thread1 = new Thread(this::put, "t1");
        Thread thread2 = new Thread(this::take, "t2");
        thread1.start();
        thread2.start();
        System.out.println("main thread finished");
    }

}
