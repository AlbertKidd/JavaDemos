package com.demos.spring;

import lombok.extern.log4j.Log4j2;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

@Log4j2
public class MethodTimeWrapper implements MethodInterceptor {


    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = methodInvocation.proceed();
        stopWatch.stop();
        long timeMillis = stopWatch.getTotalTimeMillis();
//        if (timeMillis > 100)
            log.info("used time : " + timeMillis);
        return proceed;
    }
}
