package com.kidd.demos.mybatis;

import com.kidd.demos.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.function.Supplier;

/**
 * @author Kidd
 */
@org.springframework.context.annotation.Configuration
@ImportResource("classpath:common/springContext.xml")
public class MyBatisBean implements InitializingBean, ApplicationContextAware {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Setter
    private ApplicationContext applicationContext;

    @Getter
    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;
    
    private PersonMapper getPersonMapper(){
        return sqlSessionTemplate.getMapper(PersonMapper.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void testTx(){
        Person person = getPersonMapper().getPerson("1");
        System.out.println("第一次获取：" + person);

        int originAge = person.getAge();
        person.setAge(originAge + 1);
        getPersonMapper().updatePerson(person);

        Person person1 = getPersonMapper().getPerson("1");
        System.out.println("更新后：" + person1);

        getPersonMapper().getPerson("1");


        Person person2 = getWithTx(Propagation.REQUIRES_NEW);
        System.out.println("第二次获取，编程式新事务：" + person2);
        Assert.isTrue(originAge == person2.getAge());

        MyBatisBean bean = applicationContext.getBean(MyBatisBean.class);
        Person person3 = bean.getPersonNewTx();
        System.out.println("第三次获取，注解式新事务：" + person3);
        Assert.isTrue(originAge == person3.getAge());

        Person person4 = getWithTx(Propagation.NESTED);
        System.out.println("第四次获取，编程式嵌套事务：" + person4);
        Assert.isTrue(originAge == person4.getAge() - 1);

        Person person5 = getWithTx(Propagation.NOT_SUPPORTED);
        System.out.println("第五次获取，编程式无事务：" + person5);
        Assert.isTrue(originAge == person5.getAge());

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Person getPersonNewTx(){
        return getPersonMapper().getPerson("1");
    }

    private Person getWithTx(Propagation propagation){
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(propagation.ordinal());
        return transactionTemplate.execute(new TransactionCallback<Person>() {
            @Override
            public Person doInTransaction(TransactionStatus transactionStatus) {
                return getPersonMapper().getPerson("1");
            }
        });
    }

    public <T> T runWithTx(Propagation propagation, Supplier<T> supplier){
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(propagation.ordinal());
        return transactionTemplate.execute(new TransactionCallback<T>() {
            @Override
            public T doInTransaction(TransactionStatus transactionStatus) {
                return supplier.get();
            }
        });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void testWithJdbc(){
        Person person = getPersonMapper().getPerson("1");
        System.out.println("第一次获取：" + person);

        jdbcTemplate.update("update person set age = ? where id = 1", person.getAge() + 1);

        Person person1 = getPersonMapper().getPerson("1");
        System.out.println("第二次获取：" + person1);
        Assert.isTrue(person.equals(person1));

        sqlSessionTemplate.clearCache();
        Person person2 = getPersonMapper().getPerson("1");
        System.out.println("第三次获取：" + person2);
        Assert.isTrue(!person.equals(person2));

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Environment environment =
                new Environment("test", new SpringManagedTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(PersonMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
    }

    public SqlSessionTemplate getSqlSessionTemplate(ExecutorType executorType){
        return new SqlSessionTemplate(sqlSessionFactory, executorType);
    }
}
