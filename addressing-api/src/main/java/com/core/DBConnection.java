package com.core;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.model.DAdatabase;

import com.config.SpringMongoConfig;


public class DBConnection {
    // For XML
    //ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
    // For Annotation
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    public void insert(DAdatabase da1){
      //  DAdatabase da = new DAdatabase(123.6,436.94,"d","POINT","UNIVERSITY","RELAIBILITY","J","2","34");
        // save
        mongoOperation.save(da1);
        System.out.println("1. digital address : " + da1);
    }

    public DAdatabase search(String field,String value){
        // query to search user
        //Query searchUserQuery = new Query(Criteria.where("description").is("UNIVERSITY"));
        Query searchUserQuery = new Query(Criteria.where(field).is(value));
        // find the saved one
        DAdatabase savedDA = mongoOperation.findOne(searchUserQuery, DAdatabase.class);
        System.out.println("2. Search query object : " + savedDA);
        return savedDA;
    }
    public DAdatabase searchDA(String field1,String value1,String field2,String value2,String field3,String value3){
        // query to search user
        //Query searchUserQuery = new Query(Criteria.where("description").is("UNIVERSITY"));
        Query searchUserQuery = new Query(Criteria.where(field1).is(value1).and(field2).is(value2).and(field3).is(value3));
        // find the saved one
        DAdatabase savedDA = mongoOperation.findOne(searchUserQuery, DAdatabase.class);
        System.out.println("2. Search query object : " + savedDA);
        return savedDA;
    }

    public void Update(String field,String value,String updatefiled,String updateValue){
       // Query searchUserQuery = new Query(Criteria.where("description").is("UNIVERSITY"));
        Query searchUserQuery = new Query(Criteria.where(field).is(value));
        // update password
        //mongoOperation.updateFirst(searchUserQuery,Update.update("type", "BUILDING"),DAdatabase.class);
        mongoOperation.updateFirst(searchUserQuery,Update.update(updatefiled, updateValue),DAdatabase.class);
        // find the updated user object
        DAdatabase updatedDA = mongoOperation.findOne(searchUserQuery, DAdatabase.class);
        System.out.println("3. After updating  : " + updatedDA);
    }



    public void Delete(String field,String value){
        Query searchUserQuery = new Query(Criteria.where(field).is(value));
        // delete
        mongoOperation.remove(searchUserQuery, DAdatabase.class);
    }

    public void getCount(){

        List<DAdatabase> listUser = mongoOperation.findAll(DAdatabase.class);
        System.out.println("4. Number of records = " + listUser.size());
    }

}
