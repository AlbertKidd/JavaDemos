package com.demos.origin.classLoader;

import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Kidd
 *         CreateTime 2017/8/19.
 */
public class URLClassLoaderDemo {

    @Test
    public void test(){
        File file = new File("lib/");
        try {
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
            Class<?> classLog4j2Demo = urlClassLoader.loadClass("com.demos.log4j.Log4j2Demo");
            Thread.currentThread().setContextClassLoader(urlClassLoader);
            Object instance = classLog4j2Demo.newInstance();
            Method funcSimpleLog = classLog4j2Demo.getDeclaredMethod("simpleLog", null);
            funcSimpleLog.invoke(instance, null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
