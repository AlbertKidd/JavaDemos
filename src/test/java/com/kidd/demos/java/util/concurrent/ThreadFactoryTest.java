package com.kidd.demos.java.util.concurrent;

import com.kidd.demos.java.lang.ThreadGroupTest;

import java.util.concurrent.ThreadFactory;

/**
 * @author Kidd
 */
public class ThreadFactoryTest {

    public static ThreadFactory THREAD_FACTORY = r -> new Thread(ThreadGroupTest.THREAD_GROUP, r, "TEST-T");

}
