package com.kidd.demos.log4j;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Kidd
 *         CreateTime 2017/8/9.
 */
public class LogConnectionFactory {

    private final DataSource dataSource;

    private static interface Singleton{
        final LogConnectionFactory INSTANCE = new LogConnectionFactory();
    }

    private LogConnectionFactory(){
        this.dataSource = setupDatasource();
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }

    private DataSource setupDatasource(){
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "admin");

        try {
            properties.load(new FileInputStream("src/main/resources/conf/jdbc.properties"));
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                "jdbc:mysql://127.0.0.1:3306/demo1",
                properties
        );
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);
        GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(pool);
        return new PoolingDataSource(pool);
    }
}
