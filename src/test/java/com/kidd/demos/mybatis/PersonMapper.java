package com.kidd.demos.mybatis;

import com.kidd.demos.model.Person;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Kidd
 */
public interface PersonMapper {

    @Select("SELECT * FROM PERSON WHERE id = #{id}")
    Person getPerson(String id);

    @Select("SELECT * FROM PERSON WHERE id = #{id}")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    Person getPersonNoCache(String id);

    @Update("UPDATE PERSON SET name = #{name}, age = #{age}, birthday = #{birthday,jdbcType=DATE} where id = #{id}")
    int updatePerson(Person person);

    @Insert("INSERT INTO PERSON (id, name, age, birthday) values (#{id}, #{name}, #{age}, #{birthday})")
    int insertPerson(Person person);
}
