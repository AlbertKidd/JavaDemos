package com.kidd.demos.io.file;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Kidd
 */
@Log4j2
public class FileFilterDemo {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        Date overdueDate = calendar.getTime();
        FileFilter fileFilter = FileFilterUtils.and(FileFilterUtils.ageFileFilter(overdueDate), FileFilterUtils.fileFileFilter());
        File folder = new File("E:\\Download");
        File[] files = folder.listFiles(fileFilter);
        for (File file : files){
            log.info("path:[{}], lastModifiedTime:{}", file.getPath(), DateFormatUtils.format(file.lastModified(), "yyyy-MM-dd"));
        }
    }

}
