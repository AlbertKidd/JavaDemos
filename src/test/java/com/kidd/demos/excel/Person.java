package com.kidd.demos.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Kidd
 */
@Data
public class Person {

    @ExcelProperty("id")
    private int id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("生日")
    private Date birth;
}
