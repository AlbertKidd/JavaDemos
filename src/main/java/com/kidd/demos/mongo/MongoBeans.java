package com.kidd.demos.mongo;

import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Kidd
 */
@EnableMongoRepositories
@Configuration
public class MongoBeans {

    @Bean
    public MongoClientFactoryBean mongo(){
        MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
        factoryBean.setHost("localhost");
        factoryBean.setPort(27017);
        MongoCredential credential = MongoCredential.createCredential("root", "admin", "admin".toCharArray());
        factoryBean.setCredential(new MongoCredential[]{credential});
        return factoryBean;
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongo){
        return new MongoTemplate(mongo, "ebeit");
    }

    @Bean
    public MongoDemo mongoDemo(){
        return new MongoDemo();
    }
}
