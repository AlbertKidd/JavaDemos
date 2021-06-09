package com.kidd.demos.mybatis;

import com.kidd.demos.model.Person;
import lombok.Getter;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.function.Supplier;

/**
 * @author Kidd
 */
@org.springframework.context.annotation.Configuration
@ImportResource("classpath:common/springContext.xml")
public class MyBatisBean implements InitializingBean {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Getter
    private SqlSessionFactory sqlSessionFactory;

    private SqlSessionTemplate sqlSessionTemplate;
    
    private PersonMapper getPersonMapper(){
        return sqlSessionTemplate.getMapper(PersonMapper.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Person getPersonNewTx(String id){
        return getPersonMapper().getPerson(id);
    }

    public void runWithTx(Propagation propagation, Runnable runnable){
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(propagation.ordinal());
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                runnable.run();
                return null;
            }
        });
    }

    public <T> T getWithTx(Propagation propagation, Supplier<T> supplier){
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(propagation.ordinal());
        return transactionTemplate.execute(new TransactionCallback<T>() {
            @Override
            public T doInTransaction(TransactionStatus transactionStatus) {
                return supplier.get();
            }
        });
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

    public PersonMapper getPersonMapper(ExecutorType executorType){
        SqlSessionTemplate sqlSessionTemplate = getSqlSessionTemplate(executorType);
        return sqlSessionTemplate.getMapper(PersonMapper.class);
    }
}
