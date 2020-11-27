package com.kidd.demos.apache.commons.configuration;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.io.FileWriter;

/**
 * 测试目的：写入properties文件时不对原有注释进行修改
 * @author Kidd
 */
public class ConfigurationDemo {

    public static void main(String[] args) throws Exception{
        String filePath = ConfigurationDemo.class.getResource("/common/jdbc.properties").getPath();
        Configurations configurations = new Configurations();

        PropertiesConfiguration config = configurations.properties(filePath);
        config.setProperty("jdbc.username", "wanwan");
        config.setProperty("test", "value");
        config.write(new FileWriter(filePath));
    }

}
