package com.kidd.demos.spring;

import com.kidd.demos.spring.db.DataSourceHolder;
import com.kidd.demos.spring.db.SimpleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;

/**
 * @author Kidd
 * CreateTime 2018/3/7.
 */
@Log4j2
public class AbstractRoutingDataSourceTest {

    private ClassPathXmlApplicationContext context;
    private SimpleDao simpleDao;

    @BeforeTest
    public void init(){
        context = new ClassPathXmlApplicationContext("db/springContext.xml");
        simpleDao = (SimpleDao) context.getBean("simpleDao");
    }

    @Test
    public void testInOrder(){
        simpleDao.execute("should be in db1");
        DataSourceHolder.setDataSources("db2");
        simpleDao.execute("should be in db2");
        DataSourceHolder.setDataSources("db3");
        simpleDao.execute("should be in db3");
    }

    @Test
    public void testInChaos() throws Exception{

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                DataSourceHolder.setDataSources("db2");
                simpleDao.execute("should be in db2");
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                DataSourceHolder.setDataSources("db3");
                simpleDao.execute("should be in db3");
            }
        };

        Executors.newSingleThreadExecutor().execute(runnable1);
        Executors.newSingleThreadExecutor().execute(runnable2);
        Thread.sleep(100 * 1000);

    }

}
