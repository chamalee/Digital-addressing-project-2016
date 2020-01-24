package com.core;

/**
 * Created by user on 11/10/2015.
 */


import java.util.List;

import com.model.osmpoints;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.config.SpringMongoConfig;

public class App3 {
    public static void main(String ar[]){
        // For XML
        //ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

        // For Annotation
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

      // osmpoints osmp = new osmpoints("1","kothmale", 79.8658999999999960,6.8513479999999998,"ROAD");

        // save
       // mongoOperation.save(osmp);


        // now user object got the created id.
        //System.out.println("1. digital address : " + osmp);

        // query to search user
        Query searchUserQuery = new Query(Criteria.where("description").is("ROAD"));

        // find the saved user again.
        osmpoints savedUser = mongoOperation.findOne(searchUserQuery, osmpoints.class);
        System.out.println("2. find - savedDA : " + savedUser);

        // update password
     //   mongoOperation.updateFirst(searchUserQuery,
        //        Update.update("id", "2"),osmpoints.class);

        // find the updated user object
       // osmpoints updatedUser = mongoOperation.findOne(searchUserQuery, osmpoints.class);

        //System.out.println("3. After updating id : " + updatedUser);

        // delete
        //  mongoOperation.remove(searchUserQuery, DAdatabase.class);

        // List, it should be empty now.
        List<osmpoints> listUser = mongoOperation.findAll(osmpoints.class);
        System.out.println("4. Number of records = " + listUser.size());



    }
}
