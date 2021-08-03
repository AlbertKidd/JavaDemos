package com.kidd.demos.java.util.concurrent;

import com.kidd.demos.java.lang.ThreadGroupTest;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Kidd
 */
public class ThreadFactoryTest {

    private static AtomicInteger threadCount = new AtomicInteger();

    public static ThreadFactory THREAD_FACTORY = r -> new Thread(ThreadGroupTest.THREAD_GROUP, r, "TEST-T" + threadCount.getAndIncrement());

}
