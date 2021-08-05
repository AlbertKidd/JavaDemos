package com.kidd.demos.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * @author Kidd
 */
public class SeleniumTest {

    WebDriver webDriver = new ChromeDriver();

    static {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
    }


    @Test
    public void location(){
        webDriver.get("http://www.baidu.com");
    }
}
