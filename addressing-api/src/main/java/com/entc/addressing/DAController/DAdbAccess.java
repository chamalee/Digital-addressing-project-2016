/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entc.addressing.DAController;



import com.core.DBConnection;
import com.entc.addressing.digital.address.generator.DigitalAddress;
import java.util.HashMap;
import com.entc.addressing.digital.address.generator.*;
import com.model.DAdatabase;


public class DAdbAccess {
     public DigitalAddress searchDA(DigitalAddress DA) {
         System.out.println(DA.getType());
         //System.out.println(DA.getAttribute("name"));
         System.out.println("search-da" + DA.getDigitalAddress());
         System.out.println("search-type" + DA.getType());
         System.out.println("search-datag1" + DA.getDigitalAddresTag1());
         System.out.println("search-attribute-name" + DA.getAttribute("name"));
         DBConnection dbc = new DBConnection();
         DAdatabase d = dbc.searchDA("digitalAddress1", DA.getDigitalAddresTag1(), "digitalAddress2", DA.getDigitalAddressTag2(), "digitalAddress3", DA.getDigitalAddressTag3());
         if (d!= null) {
             System.out.println("afterdb connection" + d.getDigitalAddress1());
             if(DA.getLocation().getLatitude()==0) {
                 DA.setReliability(d.getreliability());
                 DA.setType(d.getType());
                 DA.setLocationLatitude(d.getlat());
                 DA.setLocationLongtitude(d.getlon());
                 DA.setAttributes(d.getAttributes());
             }
            return  DA;
            // return new DigitalAddress(d.getlat(), d.getlat(), d.getAttributes(), d.getType(), d.getreliability(), d.getDigitalAddress1(), d.getDigitalAddress2(), d.getDigitalAddress3());
         }
         else
             return  null;
     }
     
     public int insertDA(DigitalAddress DA){
         System.out.println("insert"+DA.getDigitalAddress());
         System.out.println("insert"+DA.getDigitalAddresTag1());
         DBConnection dbc= new DBConnection();
         dbc.insert(new DAdatabase(DA.getLocation().getLatitude(),DA.getLocation().getLongtitude(),DA.getAttributes(),DA.getType(),DA.getReliability(),DA.getDigitalAddresTag1(),DA.getDigitalAddressTag2(),DA.getDigitalAddressTag3()));
         return 1;
     }
    
}
