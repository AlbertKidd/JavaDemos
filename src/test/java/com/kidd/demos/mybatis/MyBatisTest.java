package com.kidd.demos.mybatis;

import com.kidd.demos.model.Person;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Random;

/**
 * @author Kidd
 */
@Log4j2
public class MyBatisTest {

    private MyBatisBean myBatisBean;

    @BeforeTest
    private void init(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisBean.class);
        myBatisBean = applicationContext.getBean(MyBatisBean.class);
    }


    @Test
    public void testTx(){
        myBatisBean.testTx();
    }

    @Test
    public void testWithJdbc(){
        myBatisBean.testWithJdbc();
    }

    @Test
    public void testSelectProficiency(){
        SqlSessionTemplate sqlSessionTemplate = myBatisBean.getSqlSessionTemplate(ExecutorType.SIMPLE);
        PersonMapper mapper = sqlSessionTemplate.getMapper(PersonMapper.class);

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            log.info("无缓存查询10k次开始");
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++){
                mapper.getPersonNoCache("1");
            }
            log.info("无缓存查询10k次结束，耗时: " + (System.currentTimeMillis() - t1));
            return null;
        });

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            log.info("缓存查询10k次开始");
            long t2 = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++){
                mapper.getPerson("1");
            }
            log.info("缓存查询10k次结束，耗时: " + (System.currentTimeMillis() - t2));
            return null;
        });
    }

    @Test
    public void testInsertProficiency(){
        SqlSessionTemplate simpleTemplate = myBatisBean.getSqlSessionTemplate(ExecutorType.SIMPLE);
        PersonMapper simpleMapper = simpleTemplate.getMapper(PersonMapper.class);

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            log.info("普通新增1k条数据开始");
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++){
                Person person = new Person();
                person.setId(String.valueOf(i));
                person.setName("kidd" + i);
                person.setAge(i);
                person.setBirthday(new Date());
                simpleMapper.insertPerson(person);
                if (i == 500){
                    log.info(simpleMapper.getPerson(person.getId()));
                }
            }
            log.info("普通新增1k条数据结束，耗时: " + (System.currentTimeMillis() - t1));
            return null;
        });

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            SqlSessionTemplate batchTemplate = myBatisBean.getSqlSessionTemplate(ExecutorType.BATCH);
            PersonMapper batchMapper = batchTemplate.getMapper(PersonMapper.class);
            log.info("批量新增1k条数据开始");
            long t2 = System.currentTimeMillis();
            for (int i = 1000; i < 2000; i++){
                Person person = new Person();
                person.setId(String.valueOf(i));
                person.setName("kidd" + i);
                person.setAge(i);
                person.setBirthday(new Date());
                batchMapper.insertPerson(person);
                if (i == 1500){
                    log.info(batchMapper.getPerson(person.getId()));
                }
            }
            log.info("批量新增1k条数据结束，耗时: " + (System.currentTimeMillis() - t2));
            return null;
        });

    }

}
