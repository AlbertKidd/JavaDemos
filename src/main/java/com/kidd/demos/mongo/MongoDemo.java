package com.kidd.demos.mongo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Kidd
 */
public class MongoDemo {

    @Autowired
    @Getter
    private MongoRepository<Customer, String> customerDao;

    @Autowired
    @Getter
    private CustomerDao customerDao2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MongoBeans.class);
        MongoDemo bean = applicationContext.getBean(MongoDemo.class);
        MongoRepository<Customer, String> customerDao = bean.getCustomerDao();
        Customer customer = new Customer();
        customer.setFirstName("Albert");
        customer.setLastName("Kidd");
        customerDao.insert(customer);
        List<Customer> all = customerDao.findAll(Example.of(customer));

        CustomerDao customerDao2 = bean.getCustomerDao2();
        Customer albert = customerDao2.findByFirstName("Albert");
        List<Customer> kidd = customerDao2.findByLastName("Kidd");


    }
}
