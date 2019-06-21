package com.kidd.demos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Kidd
 */
public interface CustomerDao extends MongoRepository<Customer, String> {

    /**
     * 根据firstName查找Customer对象
     * @param firstName
     * @return
     */
    public Customer findByFirstName(String firstName);

    /**
     * 根据lastName查找Customer对象列表
     * @param lastName
     * @return
     */
    public List<Customer> findByLastName(String lastName);
}
