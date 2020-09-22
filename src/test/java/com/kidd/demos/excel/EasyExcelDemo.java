package com.kidd.demos.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Kidd
 */
public class EasyExcelDemo {

    private static final String EXCEL_FILE_PATH = "C:\\Users\\hasee\\Desktop\\test.xlsx";


    @Test
    public void writeTest(){
        ExcelWriter excelWriter = EasyExcel.write(EXCEL_FILE_PATH).head(getColumns()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        List<List<Object>> data = getData();

        List<List<Object>> temp = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (i % 10000 != 0){
                temp.add(data.get(i));
            }else {
                excelWriter.write(temp, writeSheet);
                temp.clear();
            }

        }
        excelWriter.finish();

    }

    @Test
    public void beanWriteTest(){
        EasyExcel.write(EXCEL_FILE_PATH, Person.class)
                .sheet()
                .doWrite(getBeanData());
    }

    private List<List<Object>> getData(){
        List<List<Object>> data = new ArrayList<>();
        for (int i = 0; i < 1000 * 1000; i++){
            List<Object> list = new ArrayList<>();
            list.add(i);
            list.add("姓名" + i);
            long randomMillis = Math.round((Math.random() + 1) * 1000 * 1000 * 1000 * 1000);
            list.add(new Date(randomMillis));
            data.add(list);
        }
        return data;
    }

    private List<Person> getBeanData(){
        List<Person> data = new ArrayList<>();
        for (int i = 0; i < 200; i++){
            Person person = new Person();
            person.setId(i);
            person.setName("姓名" + i);
            long randomMillis = Math.round((Math.random() + 1) * 1000 * 1000 * 1000 * 1000);
            person.setBirth(new Date(randomMillis));
            data.add(person);
        }
        return data;
    }

    private List<List<String>> getColumns(){
        List<List<String>> columns = new ArrayList<>();
        columns.add(Collections.singletonList("id"));
        columns.add(Collections.singletonList("name"));
        columns.add(Collections.singletonList("birth"));
        return columns;
    }

}
