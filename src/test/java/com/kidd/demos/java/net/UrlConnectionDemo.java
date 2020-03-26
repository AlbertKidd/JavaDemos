package com.kidd.demos.java.net;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Kidd
 */
public class UrlConnectionDemo {

    public void test() throws Exception{
        URL url = new URL("http://www.baidu.com");
        URLConnection urlConnection = url.openConnection();
        OutputStream outputStream = urlConnection.getOutputStream();
        File file = new File("C:\\Users\\hasee\\Desktop\\2645-招待费报销单.xml");
        FileReader fileReader = new FileReader(file);
    }
}
