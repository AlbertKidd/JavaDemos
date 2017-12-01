package com.kidd.demos.aspectj;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Log4j2
@Aspect
public class CustomAspect {

    @Before("execution(public * *(..))")
    public void before(JoinPoint joinPoint){
        log.info(joinPoint + " is starting ------------------------");
    }

    @After("execution(public * *(..))")
    public void after(JoinPoint joinPoint){
        log.info(joinPoint + " is finished ------------------------");
    }
}
