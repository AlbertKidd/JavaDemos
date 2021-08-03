package com.kidd.demos.mybatis;

import com.kidd.demos.model.Person;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kidd
 */
@Log4j2
public class MyBatisTest {

    private MyBatisBean myBatisBean;
    
    private JdbcTemplate jdbcTemplate;

    @BeforeTest
    private void init(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisBean.class);
        myBatisBean = applicationContext.getBean(MyBatisBean.class);
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }

    @BeforeMethod
    private void truncateTable(){
        jdbcTemplate.update("truncate person");
    }

    @Test
    public void testTx(){
        PersonMapper mapper = myBatisBean.getPersonMapper(ExecutorType.SIMPLE);

        mapper.insertPerson(newPerson(1));
        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            Person person = mapper.getPerson("1");
            String id = person.getId();
            System.out.println("第一次获取：" + person);

            int originAge = person.getAge();
            person.setAge(originAge + 1);
            mapper.updatePerson(person);

            Person person1 = mapper.getPerson("1");
            System.out.println("更新后：" + person1);

            Person person2 = myBatisBean.getWithTx(Propagation.REQUIRES_NEW, () -> mapper.getPerson(id));
            System.out.println("第二次获取，编程式新事务：" + person2);
            Assert.isTrue(originAge == person2.getAge());

            Person person3 = myBatisBean.getPersonNewTx(id);
            System.out.println("第三次获取，注解式新事务：" + person3);
            Assert.isTrue(originAge == person3.getAge());

            Person person4 = myBatisBean.getWithTx(Propagation.NESTED, () -> mapper.getPerson(id));
            System.out.println("第四次获取，编程式嵌套事务：" + person4);
            Assert.isTrue(originAge == person4.getAge() - 1);

            Person person5 = myBatisBean.getWithTx(Propagation.NOT_SUPPORTED, () -> mapper.getPerson(id));
            System.out.println("第五次获取，编程式无事务：" + person5);
            Assert.isTrue(originAge == person5.getAge());
        });
    }

    @Test
    public void testWithJdbc(){
        SqlSessionTemplate sqlSessionTemplate = myBatisBean.getSqlSessionTemplate(ExecutorType.SIMPLE);
        PersonMapper mapper = myBatisBean.getPersonMapper(ExecutorType.SIMPLE);

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            Person person = newPerson(1);
            String id = person.getId();
            mapper.insertPerson(person);

            person = mapper.getPerson(id);
            System.out.println("第一次获取：" + person);

            jdbcTemplate.update("update person set age = ? where id = ?", person.getAge() + 1, id);

            Person person1 = mapper.getPerson(id);
            System.out.println("第二次获取：" + person1);
            Assert.isTrue(person.equals(person1));

            sqlSessionTemplate.clearCache();
            Person person2 = mapper.getPerson(id);
            System.out.println("第三次获取：" + person2);
            Assert.isTrue(!person.equals(person2));
        });
    }

    @Test
    public void testSelectProficiency(){
        PersonMapper mapper = myBatisBean.getPersonMapper(ExecutorType.SIMPLE);

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            log.info("无缓存查询10k次开始");
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++){
                mapper.getPersonNoCache("1");
            }
            log.info("无缓存查询10k次结束，耗时: " + (System.currentTimeMillis() - t1));
        });

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            log.info("缓存查询10k次开始");
            long t2 = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++){
                mapper.getPerson("1");
            }
            log.info("缓存查询10k次结束，耗时: " + (System.currentTimeMillis() - t2));
        });
    }

    @Test
    public void testInsertProficiency(){
        PersonMapper simpleMapper = myBatisBean.getPersonMapper(ExecutorType.SIMPLE);

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            log.info("普通新增1k条数据开始");
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++){
                Person person = newPerson(i);
                simpleMapper.insertPerson(person);
                if (i == 500){
                    log.info(simpleMapper.getPerson(person.getId()));
                }
            }
            log.info("普通新增1k条数据结束，耗时: " + (System.currentTimeMillis() - t1));
        });

        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            PersonMapper batchMapper = myBatisBean.getPersonMapper(ExecutorType.BATCH);
            log.info("批量新增1k条数据开始");
            long t2 = System.currentTimeMillis();
            for (int i = 1000; i < 2000; i++){
                Person person = newPerson(i);
                batchMapper.insertPerson(person);
                if (i == 1500){
                    log.info(batchMapper.getPerson(person.getId()));
                }
            }
            log.info("批量新增1k条数据结束，耗时: " + (System.currentTimeMillis() - t2));
        });

    }

    @Test
    public void testRollback(){
        SqlSessionTemplate batchTemplate = myBatisBean.getSqlSessionTemplate(ExecutorType.BATCH);
        PersonMapper batchMapper = batchTemplate.getMapper(PersonMapper.class);
        myBatisBean.runWithTx(Propagation.REQUIRED, () -> {
            Person person1 = newPerson(1);
            batchMapper.insertPerson(person1);

            try {
                myBatisBean.runWithTx(Propagation.REQUIRES_NEW, () -> {
                    person1.setAge(person1.getAge() + 1);
                    batchMapper.updatePerson(person1);
                    throw new RuntimeException("intentionally throwing");
                });
            }catch (Exception e){
                // do nothing
            }
        });
        Person person = batchMapper.getPerson("1");
        Assert.isTrue(person.getAge() == 1);
    }

    @Test
    public void testConcurrent() throws Exception{
        PersonMapper batchMapper = myBatisBean.getPersonMapper(ExecutorType.BATCH);

        Thread thread1 = new Thread(() -> {
            Person person = batchMapper.getPerson("1");
            Assert.isNull(person);
        });

        Thread thread2 = new Thread(() -> {
            batchMapper.insertPerson(newPerson(1));
            try {
                thread1.join();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });

        List<Thread> threads = new ArrayList<>();
        threads.add(thread1);
        threads.add(thread2);

        for (Thread thread : threads) {
            thread.start();
        }

        thread1.join();
    }

    private Person newPerson(int age){
        Person person = new Person();
        person.setId(String.valueOf(age));
        person.setName("kidd" + age);
        person.setAge(age);
        person.setBirthday(new Date());
        return person;
    }

}
