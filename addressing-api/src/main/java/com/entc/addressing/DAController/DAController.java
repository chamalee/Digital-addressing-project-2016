/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entc.addressing.DAController;
import com.entc.addressing.digital.address.generator.DigitalAddress;
import com.entc.addressing.digital.address.generator.DigitalAddressGenerator;
import com.entc.addressing.digital.address.generator.GeoLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DAController {
        private DigitalAddress DAObject; 
        private ArrayList<String> aList;
    
    
 
    public HashMap registerDA(HashMap request){
        HashMap<String, String> response = new HashMap<>();
        try {

            String rType = (String) request.get("rType");
          //  String type=(String) request.get("type");
           // DAObject.setType(type);
         //   DAObject.setType((String) request.get("type"));
          // System.out.println("inside_controller"+DAObject.getType());
            //DAObject.setType((String) request.get("type"));
            if (rType.equals("1")) {
                Types t = new Types();
                GeoLocation p = new GeoLocation(Double.valueOf((String) request.get("latitude")), Double.valueOf((String) request.get("longtitude")));
                DigitalAddressGenerator generator = new DigitalAddressGenerator(p);
                DAObject = generator.generateDigitalAddress();
                DAdbAccess access = new DAdbAccess();
               // System.out.println("inside controller"+DAObject.getDigitalAddresTag1());
            //
                Object o=access.searchDA(DAObject);
                if ( o== null) {
                    aList = t.getType((String) request.get("type"));
                    Iterator<String> aListIterator = aList.iterator();
                    GeodbAccess geoAccess = new GeodbAccess();
                    HashMap<String, String> dbInfo = geoAccess.getInfo(p, (String) request.get("type"));
                    HashMap<String, String> attributes = (HashMap) request.get("attributes");


                    while (aListIterator.hasNext()) {
                        String key = aListIterator.next();
                        System.out.println(key);
                        if (attributes.containsKey(key)) {
                            DAObject.addAttributes(key, attributes.get(key));
                        }
                        if (dbInfo != null) {

                            if (dbInfo.containsKey(key)) {

                                if (DAObject.getAttributes().containsKey(key) && ((String) request.get("user")) != "admin") {
                                    DAObject.updateAttribute(key, dbInfo.get(key));
                                } else {
                                    DAObject.addAttributes(key, dbInfo.get(key));
                                }

                            }

                        }


                    }

                    System.out.println("inside controlleeeee"+DAObject.getDigitalAddress());
                    if (access.insertDA(DAObject) == 1) {

                        response.put("Status", "Success");
                        return response;
                    }
                    response.put("Status", "dbconnection failed");
                    return response;


                }

                    //return da already exists
                    response.put("Status", "DA Exist");
                    return response;




            }

            // request type not recognized
            response.put("Status", "rType not recognized");
            return response;
        }
        catch (Exception e){
            System.out.printf(e.toString());
            response.put("exception",e.getLocalizedMessage());
            return response;

        }

       
    }


    public HashMap searchDA(HashMap request){
        DAObject = new DigitalAddress();

        HashMap response = new HashMap();
        try {
            String rType = (String) request.get("rType");

            switch (rType) {
                case "1":
                    DigitalAddressGenerator daGenerator = new DigitalAddressGenerator();
                    //System.out.println(request.get("tag1"));
                    String tag1 = (String) request.get("tag1");

                    String tag2 = (String) request.get("tag2");
                    String tag3 = (String) request.get("tag3");
                    //System.out.println(tag3);
                    if ((tag1 != null) && (tag2 != null) && (tag3 != null)) {
                        //System.out.println(tag1);
                        DAObject.setDigitalAddressTag1(tag1);
                        DAObject.setDigitalAddressTag2(tag2);
                        DAObject.setDigitalAddressTag3(tag3);
                        DAdbAccess access = new DAdbAccess();
                        DAObject = access.searchDA(DAObject);
                        if (DAObject == null) {
                            String[] tags = new String[3];
                            tags[0] = tag1;
                            tags[1] = tag2;
                            tags[2] = tag3;
                            GeoLocation[] polygon = daGenerator.getPolygon(tags);

                            System.out.println("$$$");
                            response.put("tag1", tag1);
                            response.put("tag2", tag2);
                            response.put("tag3", tag3);
                            response.put("Polygon", polygon);
                            response.put("Status", "Unregistered DA");
                            return response;

                        }
                        response.put("DAObect", DAObject);
                        response.put("Status", "Success");
                        return response;

                    } else if (tag3 == null) {
                        String[] tags = new String[2];
                        tags[0] = tag1;
                        tags[1] = tag2;
                        GeoLocation[] polygon = daGenerator.getPolygon(tags);
                        response.put("tag1", DAObject.getDigitalAddresTag1());
                        response.put("tag2", DAObject.getDigitalAddressTag2());
                        response.put("Polygon", polygon);
                        response.put("Status", "Incomplete DA");
                        return response;
                    } else if (tag3 == null && tag2 == null) {
                        String[] tags = new String[1];
                        tags[0] = tag1;
                        GeoLocation[] polygon = daGenerator.getPolygon(tags);
                        // return statement
                        response.put("tag1", DAObject.getDigitalAddresTag1());
                        response.put("Polygon", polygon);
                        response.put("Status", "Incomplete DA");
                        return response;
                    }
                    //return statement error
                    response.put("status", "error");
                    return response;


                case "2":

                    String lat = (String) request.get("latitude");
                    String lng = (String) request.get("longtitude");

                    if (lat != null && lng != null) {
                        DAObject.setLoacation(Double.valueOf(lat), Double.valueOf(lng));
                        DigitalAddressGenerator Generator = new DigitalAddressGenerator(DAObject.getLocation());
                        DAObject = Generator.generateDigitalAddress();
                        DAdbAccess dbAccess = new DAdbAccess();
                        DigitalAddress temp;
                        temp = dbAccess.searchDA(DAObject);
                        if (temp != null) {
                            DAObject = temp;
                            //return statement
                            response.put("DAObect", DAObject);
                            response.put("Status", "Success");
                            return response;
                        }
                        //return statement
                        response.put("latitude", Double.valueOf(lat));
                        response.put("longtitude", Double.valueOf(lng));
                        response.put("tag1", DAObject.getDigitalAddresTag1());
                        response.put("tag2", DAObject.getDigitalAddressTag2());
                        response.put("tag3", DAObject.getDigitalAddressTag3());
                        response.put("status", "unregistered DA");
                        return response;

                    }
                    response.put("status", "error");
                    return response;

            }

            //return statement
            response.put("status", "unknown request type");
            return response;
        }
        catch (Exception e){
            response.put("exception",e.getLocalizedMessage());
            return response;
        }



    }

    
   
    

}