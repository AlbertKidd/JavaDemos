package com.kidd.demos.spring.db;

/**
 * @author Kidd
 * CreateTime 2018/3/7.
 */
public class DataSourceHolder {

    private static final ThreadLocal<String> dataSources = new ThreadLocal<>();

    public static void setDataSources(String dataSource){
        dataSources.set(dataSource);
    }

    public static String getDataSource(){
        return dataSources.get();
    }

    public static void clearDataSource(){
        dataSources.remove();
    }
}
