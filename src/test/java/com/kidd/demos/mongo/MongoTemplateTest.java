package com.kidd.demos.mongo;

import com.google.gson.Gson;
import com.kidd.demos.model.Person;
import com.kidd.demos.model.PersonCopy;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kidd
 */
@Log4j2
public class MongoTemplateTest {

    private static final String COLLECTION_PERSON = "person";

    private MongoTemplate mongoTemplate;

    private Query ageQuery;

    private Update ageUpdate;

    @BeforeTest
    public void init(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MongoBeans.class);
        mongoTemplate = applicationContext.getBean(MongoTemplate.class);
        ageQuery = getAgeQuery();
        ageUpdate = getAgeUpdate();
    }

    @Test
    public void testInsert(){
        // 直接新增对象，类名即为插入的表名
        mongoTemplate.insert(newPerson());

        // 向指定表名中插入对象
        mongoTemplate.insert(newPerson(), COLLECTION_PERSON);

        // 批量插入集合数据，表名根据数据的类名自动判断（可以是不同的类型）

        mongoTemplate.insertAll(personAndCustomer());

        // 批量插入数据，表名为指定的类名
        mongoTemplate.insert(newPersonList(), Person.class);

        // 向指定的表中批量插入数据
        mongoTemplate.insert(newPersonList(), COLLECTION_PERSON);

        // 保存数据，无则插入，有则覆盖，类似于upsert
        mongoTemplate.save(newPerson());
        mongoTemplate.save(newPerson(), COLLECTION_PERSON);

        List<Person> result = mongoTemplate.findAll(Person.class);
        log.info(new Gson().toJson(result));

        cleanCollection();
    }

    @Test
    public void testQuery(){
        // 准备数据
        mongoTemplate.insertAll(newPersonList());

        // 根据查询条件与类型进行查询，表名即为类名
        List<Person> personList1 = mongoTemplate.find(ageQuery, Person.class);

        // 从指定表中根据查询条件查询数据，并将结果转为指定类型的对象
        List<PersonCopy> personList2 = mongoTemplate.find(ageQuery, PersonCopy.class, COLLECTION_PERSON);

        // 查询所有数据，表名为类名
        List<Person> personList3 = mongoTemplate.findAll(Person.class);

        // 查询指定表的所有数据，并将结果转为指定类型的对象
        List<PersonCopy> personList4 = mongoTemplate.findAll(PersonCopy.class, COLLECTION_PERSON);

        // 找到符合条件的第一条数据，表名为类名
        Person person1 = mongoTemplate.findOne(ageQuery, Person.class);

        // 找到指定表中符合条件的第一条数据，并将结果转为指定类型的对象
        PersonCopy person2 = mongoTemplate.findOne(ageQuery, PersonCopy.class, COLLECTION_PERSON);

        // 根据id查找数据，表名为类名
        Person person3 = mongoTemplate.findById(person1.getId(), Person.class);

        // 从指定表中根据id查找数据，并将结果转为指定类型的对象
        PersonCopy person4 = mongoTemplate.findById(person1.getId(), PersonCopy.class, COLLECTION_PERSON);

        cleanCollection();
    }

    @Test
    public void testUpdate(){
        // 准备数据
        mongoTemplate.insert(newPersonList(), COLLECTION_PERSON);

        // 更新查询到的第一条数据
        UpdateResult UpdateResult1 = mongoTemplate.updateFirst(ageQuery, ageUpdate, Person.class);

        // 更新查询到的第一条数据
        UpdateResult UpdateResult2 = mongoTemplate.updateFirst(ageQuery, ageUpdate, COLLECTION_PERSON);

        // 更新查询到的第一条数据
        UpdateResult UpdateResult3 = mongoTemplate.updateFirst(ageQuery, ageUpdate, Person.class, COLLECTION_PERSON);

        // 更新查询到的所有数据
        UpdateResult UpdateResult4 = mongoTemplate.updateMulti(ageQuery, ageUpdate, Person.class);

        // 更新查询到的所有数据
        UpdateResult UpdateResult5 = mongoTemplate.updateMulti(ageQuery, ageUpdate, COLLECTION_PERSON);

        // 更新查询到的所有数据
        UpdateResult UpdateResult6 = mongoTemplate.updateMulti(ageQuery, ageUpdate, PersonCopy.class, COLLECTION_PERSON);

        // 更新根据查询条件查出的第一条记录，不存在则按照查询条件与更新函数插入一条数据
        UpdateResult UpdateResult7 = mongoTemplate.upsert(ageQuery, ageUpdate, COLLECTION_PERSON);

        // 更新根据查询条件查出的第一条记录，不存在则按照查询条件与更新函数插入一条数据
        UpdateResult UpdateResult8 = mongoTemplate.upsert(ageQuery, ageUpdate, Person.class);

        // 更新根据查询条件查出的第一条记录，不存在则按照查询条件与更新函数插入一条数据
        UpdateResult UpdateResult9 = mongoTemplate.upsert(ageQuery, ageUpdate, PersonCopy.class, COLLECTION_PERSON);

        // 根据查询条件找到并修改查到的第一条记录，返回查出的记录（修改前）
        Person modifyPerson1 = mongoTemplate.findAndModify(ageQuery, ageUpdate, Person.class);

        // 根据查询条件找到并修改查到的第一条记录，返回查出的记录（修改前）
        PersonCopy modifyPerson2 = mongoTemplate.findAndModify(ageQuery, ageUpdate, PersonCopy.class, COLLECTION_PERSON);

        // 根据查询条件找到并修改查到的第一条记录，返回查出的记录（修改后）
        Person modifyPerson3 = mongoTemplate.findAndModify(ageQuery, ageUpdate, FindAndModifyOptions.options().returnNew(true), Person.class);

        // 根据查询条件找到并修改查到的第一条记录，返回查出的记录（修改后）
        PersonCopy modifyPerson4 = mongoTemplate.findAndModify(ageQuery, ageUpdate, FindAndModifyOptions.options().returnNew(true), PersonCopy.class, COLLECTION_PERSON);

        List<Person> result = mongoTemplate.findAll(Person.class);
        log.info(new Gson().toJson(result));

        cleanCollection();
    }

    @Test
    public void testRemove(){
        // 准备数据
        Person person = newPerson();

        // 根据条件删除数据
        mongoTemplate.insert(person, COLLECTION_PERSON);
        DeleteResult remove1 = mongoTemplate.remove(ageQuery, COLLECTION_PERSON);

        // 根据条件删除数据
        mongoTemplate.insert(person, COLLECTION_PERSON);
        DeleteResult remove2 = mongoTemplate.remove(ageQuery, Person.class);

        // 根据条件删除数据
        mongoTemplate.insert(person, COLLECTION_PERSON);
        DeleteResult remove3 = mongoTemplate.remove(ageQuery, PersonCopy.class, COLLECTION_PERSON);

        // 删除指定数据
        mongoTemplate.insert(person, COLLECTION_PERSON);
        DeleteResult remove4 = mongoTemplate.remove(person);

        // 删除指定数据
        mongoTemplate.insert(person, COLLECTION_PERSON);
        DeleteResult remove5 = mongoTemplate.remove(person, COLLECTION_PERSON);

        // 根据条件找到并删除一条数据，返回找到的对象
        mongoTemplate.insert(person, COLLECTION_PERSON);
        Person removePerson1 = mongoTemplate.findAndRemove(ageQuery, Person.class);

        // 根据条件找到并删除一条数据，返回找到的对象
        mongoTemplate.insert(person, COLLECTION_PERSON);
        PersonCopy removePerson2 = mongoTemplate.findAndRemove(ageQuery, PersonCopy.class, COLLECTION_PERSON);

        // 根据条件找到并删除所有数据，返回找到的对象列表
        mongoTemplate.insert(newPersonList(), COLLECTION_PERSON);
        List<Person> removePersons1 = mongoTemplate.findAllAndRemove(ageQuery, COLLECTION_PERSON);

        // 根据条件找到并删除所有数据，返回找到的对象列表
        mongoTemplate.insert(newPersonList(), COLLECTION_PERSON);
        List<PersonCopy> removePersons2 = mongoTemplate.findAllAndRemove(ageQuery, PersonCopy.class, COLLECTION_PERSON);

        List<Person> result = mongoTemplate.findAll(Person.class);
        log.info(new Gson().toJson(result));

        cleanCollection();
    }

    @Test
    public void testCount(){
        // 准备数据
        mongoTemplate.insert(newPersonList(), COLLECTION_PERSON);

        long count1 = mongoTemplate.count(ageQuery, COLLECTION_PERSON);

        long count2 = mongoTemplate.count(ageQuery, Person.class);

        long count3 = mongoTemplate.count(ageQuery, PersonCopy.class, COLLECTION_PERSON);
        cleanCollection();
    }

    @Test
    public void testCollectionOperations(){
        boolean exists = mongoTemplate.collectionExists(COLLECTION_PERSON);

        boolean exists1 = mongoTemplate.collectionExists(Person.class);

    }

    private Person newPerson(){
        return new Person("Kidd", 20, "xi'an");
    }

    private List<Person> newPersonList(){
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            Person p = new Person("Kidd" + i, (i + 1) * 10, "address" + i);
            personList.add(p);
        }
        return personList;
    }

    private List<Object> personAndCustomer(){
        List<Object> list = new ArrayList<>();
        list.add(newPerson());
        Customer customer = new Customer();
        customer.setFirstName("Albert");
        customer.setLastName("Kidd");
        list.add(customer);
        return list;
    }

    /**
     * 年龄大于等于20的查询条件
     * @return
     */
    private Query getAgeQuery(){
        return Query.query(Criteria.where("age").gte(20));
    }

    /**
     * 更改姓名的Update
     * @return
     */
    private Update getAgeUpdate(){
        return new Update().inc("age", 1);
    }

    /**
     * 清空person表
     */
    private void cleanCollection(){
        mongoTemplate.remove(new Query(), COLLECTION_PERSON);
    }
}
