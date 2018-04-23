package com.kidd.demos.spring.jdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.MessageFormat;

public class JdbcTemplateTest {

    private ApplicationContext context;
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_CREATE_SCHEMA = "create schema {0} default character set utf8 collate utf8_general_ci";
    private static final String SQL_CREATE_USER = "create user ''{0}''@''%'' identified by ''{1}''";
    private static final String SQL_GRANT = "GRANT ALL ON {0}.* TO ''{1}''@''%''";

    @BeforeTest
    public void init(){
        context = new ClassPathXmlApplicationContext("/springContext.xml");
        jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    }

    @Test
    public void testCreateScheme(){
        jdbcTemplate.execute(MessageFormat.format(SQL_CREATE_SCHEMA, "schema1"));
    }

    @Test
    public void testCreateUser(){
        jdbcTemplate.execute(MessageFormat.format(SQL_CREATE_USER, "niuwa", "8520130"));
    }

    @Test
    public void testGrant(){
        jdbcTemplate.execute(MessageFormat.format(SQL_GRANT, "schema1", "niuwa"));
    }

    @Test
    public void testCount(){
        String sql = "SELECT COUNT(account_code) FROM sys_account WHERE account_code = '1'";
        jdbcTemplate.queryForObject(sql, Integer.class);
    }

}
