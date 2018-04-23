package com.kidd.demos.spring.db;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Kidd
 * CreateTime 2018/3/7.
 */
@Log4j2
public class SimpleDao extends JdbcDaoSupport {

    @Transactional(propagation = Propagation.REQUIRED)
    public void execute(String s){
        getJdbcTemplate().update("INSERT INTO test(NAME) VALUES (?)", s);
        getJdbcTemplate().query("select * from test", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                String s = rs.getString("name");
                log.info("********* Look at me ****** " + s);
            }
        });
        getJdbcTemplate().update("DELETE FROM test WHERE name = ?", s);
    }

}
