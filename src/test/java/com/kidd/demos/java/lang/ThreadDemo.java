package com.kidd.demos.java.lang;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

/**
 * @author Kidd
 *         CreateTime 2017/9/2.
 */
@Log4j2
public class ThreadDemo {

    Thread t3;

    @Test
    public void test1(){


        t3 = new Thread(new Runnable() {
            public void run() {
                for (;;){

                    logLog("t3");
                }
            }
        });
        t3.start();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (;;)
                    logLog("t1");
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                    for (;;)
                        logLog("t2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();

        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void logLog(String s){
        try {
            if (t3.isAlive())
                t3.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("running thread: " + s);
    }
}
