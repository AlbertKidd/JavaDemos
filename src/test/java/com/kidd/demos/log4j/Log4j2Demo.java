package com.kidd.demos.log4j;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.Test;

@Log4j2
public class Log4j2Demo {


    @Test
    public void simpleLog() {

        for (int i = 0; i < 100000; i++) {
            ThreadContext.put("context", "context is : " + i);
            log.info("time" + i + "begin ____________________________");
            log.trace("trace");
            log.debug("debug");
            log.info("info");
            log.warn("warn");
            log.error("error");
            log.info("time" + i + "finish ____________________________");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                log.error(e.toString());
            }
            ThreadContext.clearMap();
        }
    }

    @Test
    public void routingLog() throws Exception{
        Logger logger = LogManager.getLogger("routingLogger");
        LogContext.set("a");
        logger.error("what");
        LogContext.set("b");
        logger.error("what");
        LogContext.set("c");
        logger.error("what");
        LogContext.set("a");
        logger.error("what");
    }

}
