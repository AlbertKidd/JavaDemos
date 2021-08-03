package com.kidd.demos.utils;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

/**
 * @author Kidd
 */
public class MockLock {

    private volatile int state = 0;

    private Node head;

    private Node tail;

    private long stateOffset;

    private long tailOffset;

    {
        try {
            stateOffset = UnsafeUtils.getUnsafe().objectFieldOffset(MockLock.class.getDeclaredField("state"));
            tailOffset = UnsafeUtils.getUnsafe().objectFieldOffset(MockLock.class.getDeclaredField("tail"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    private Unsafe unsafe = UnsafeUtils.getUnsafe();

    /**
     * enqueue: add a node to link queue
     *
     * @return
     */
    private Node enqueue() {
        while (true) {
            Node t = tail;
            Node node = new Node(t);
            if (unsafe.compareAndSwapObject(this, tailOffset, t, node)) {
                if (t == null) {
                    head = tail;
                } else {
                    t.next = node;
                }
                return node;
            }
        }
    }

    private boolean casState() {
        return unsafe.compareAndSwapInt(this, stateOffset, 0, 1);
    }

    /**
     * remove current head node, head point to next node
     */
    public void lock() {
        if (casState()) {
            return;
        }
        Node node = enqueue();
        // 1. node not head
        // 2. node is head but can't obtain lock
        while (node != head || !casState()) {
            LockSupport.park();
        }

        head = head.next;
        if (head == null) {
            tail = head;
        } else {
            head.prev = null;
            node.next = null;
        }
    }

    /**
     * notify current head node
     */
    public void unlock() {
        state = 0;
        if (head != null) {
            LockSupport.unpark(head.thread);
        }
    }

    static class Node {

        private Thread thread;

        Node prev;

        Node next;

        Node(Node prev) {
            this.thread = Thread.currentThread();
            this.prev = prev;
        }

    }

    public static void main(String[] args) throws Exception {
        MockLock mockLock = new MockLock();
        int[] counts = new int[1];
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        Runnable runnable = () -> {
            mockLock.lock();
            IntStream.range(0, 1000).forEach(i -> {
                counts[0]++;
                countDownLatch.countDown();
            });
            mockLock.unlock();
        };
        IntStream.range(0, 10).forEach(i -> new Thread(runnable).start());
        countDownLatch.await();
        System.out.println(counts[0]);
    }
}
