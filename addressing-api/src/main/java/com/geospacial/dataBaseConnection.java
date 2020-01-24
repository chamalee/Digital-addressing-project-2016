package com.geospacial;

/**
 * Created by user on 11/16/2015.
 */
import java.net.UnknownHostException;

import com.mongodb.*;
import com.mongodb.BasicDBObject;
//import com.mongodb.BulkWriteOperation;
//import com.mongodb.BulkWriteResult;
//import com.mongodb.Cursor;
import com.mongodb.DB;

public class dataBaseConnection {

    public static DBCollection getConnnection(String collection_name){
        {
            try{
                // To connect to mongodb server
                Mongo mongoClient = new Mongo("localhost", 27017);

                // Now connect to your databases

                DB db = mongoClient.getDB( "digitaladdressdb" );
                if(db!=null){
                    System.out.println("Connect to database successfully");
                    collection_name="osmnewpoints";
                    DBCollection collection = db.getCollection(collection_name);

                    return collection;


                }

                return null;
            }catch(Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                return null;
            }
        }
    }

}
