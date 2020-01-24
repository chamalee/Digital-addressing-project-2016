package com.geospacial;

/**
 * Created by user on 11/16/2015.
 */
import com.mongodb.BasicDBList;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import java.util.LinkedList;

public class openStreetMapSearch {
    private static BasicDBObject nearestPointData(String collection_name,double longitude,double Latitude, int near_distance) {

        DBCollection collection =  dataBaseConnection.getConnnection(collection_name);

//    db.<collection>.find( { <location field> :
//                         { $near :
//                           { $geometry :
//                              { type : "Point" ,
//                                coordinates : [ <longitude> , <latitude> ] } ,
//                             $maxDistance : <distance in meters>
//                      } } } )
        BasicDBList geocord = new BasicDBList();
        geocord.add(longitude);
        geocord.add(longitude);

        BasicDBObject q= new  BasicDBObject("loc", new BasicDBObject ("$near",new BasicDBObject ("$geometry",new BasicDBObject ("type","Point").append("cordinates",geocord ).append("$maxDistance", near_distance))) );

        return q;
    }



    private static BasicDBObject withincircle(String collection_name,double longitude,double Latitude, int radius) {

        DBCollection collection =  dataBaseConnection.getConnnection(collection_name);


        final LinkedList circle = new LinkedList();
        circle.addLast(new double[]{longitude,Latitude});
        circle.addLast(radius);

        final BasicDBObject q= new  BasicDBObject("loc", new BasicDBObject ("$Within",new BasicDBObject ("$center",circle)) );

        int count =0;
        for(final DBObject venue: collection.find(q).toArray()){
            count++;
        }
        return q;
    }
}
