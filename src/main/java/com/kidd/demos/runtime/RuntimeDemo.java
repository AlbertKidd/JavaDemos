package com.kidd.demos.runtime;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kidd
 */
@Log4j2
public class RuntimeDemo {

    public static void main(String[] args) throws Exception{
        File file = new File("D:\\ebig\\lms\\code\\wms\\wms1\\wms-web\\RgChkPrintDrug_1563610570075_553812.java");
        List<String> cmd = new ArrayList<>();
        cmd.add("javac");
        cmd.add("-encoding");
        cmd.add(System.getProperty("file.encoding", "UTF-8"));
        cmd.add(file.getPath());
        Process process = Runtime.getRuntime().exec(cmd.toArray(new String[0]));
        InputStream errorStream = process.getErrorStream();
        log.info("error:{}", IOUtils.toString(errorStream, System.getProperty("sun.jnu.encoding")));
    }
}
