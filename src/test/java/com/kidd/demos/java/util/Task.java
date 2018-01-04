package com.kidd.demos.java.util;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @author Kidd
 * CreateTime 2018/1/3.
 */
@Log4j2
public class Task implements Runnable {

    @Setter
    volatile private String msg = "0";

    @Override
    public void run() {
        log.info("---- Kidd log " + msg + " ----");
    }
}
