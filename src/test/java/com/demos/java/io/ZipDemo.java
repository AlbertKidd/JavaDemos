package com.demos.java.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Kidd
 *         CreateTime 2017/10/23.
 */
public class ZipDemo {

    @Test
    public void zip() throws Exception{
        File dir = new File("d:/aa");
        File[] files = dir.listFiles();
        File zip = new File("d:/aa/demo.zip");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        for (File file : files){
            if (file.isDirectory()) continue;
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);
            InputStream is = new FileInputStream(file);
            int temp = -1;
            while ((temp = is.read()) != -1){
                zos.write(temp);
            }
            is.close();
        }
        zos.close();
    }
}
