package com.demos.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author Kidd
 *         CreateTime 2017/10/23.
 */
public class FileUtil {

    /**
     * 使用gzip压缩字节
     * @param data
     * @return
     */
    public static byte[] gzip(byte[] data){
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gos = new GZIPOutputStream(bos);
            gos.write(data);
            gos.finish();
            gos.close();
            b = bos.toByteArray();
            bos.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return b;
    }

    /**
     * 使用gzip解压字节
     * @param data
     * @return
     */
    public static byte[] ungzip(byte[] data){
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gis = new GZIPInputStream(bis);
            byte[] buffer = new byte[1024];
            int num = -1;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((num = gis.read(buffer, 0, buffer.length)) != -1){
                bos.write(buffer, 0, num);
            }
            b = bos.toByteArray();
            bos.flush();
            bos.close();
            bis.close();
            gis.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return b;
    }
}
