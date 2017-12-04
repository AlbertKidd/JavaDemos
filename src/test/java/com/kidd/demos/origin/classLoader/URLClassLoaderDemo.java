package com.kidd.demos.origin.classLoader;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Kidd
 *         CreateTime 2017/8/19.
 */
@Log4j2
public class URLClassLoaderDemo {

    @Test
    public void test(){
        File file = new File("lib/");
        try {
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
            Class<?> classLog4j2Demo = urlClassLoader.loadClass("com.kidd.demos.log4j.Log4j2Demo");
            Thread.currentThread().setContextClassLoader(urlClassLoader);
            Object instance = classLog4j2Demo.newInstance();
            Method funcSimpleLog = classLog4j2Demo.getDeclaredMethod("simpleLog", null);
            funcSimpleLog.invoke(instance, null);
        } catch (Exception e) {
            log.error(e);
        }
    }
}
