/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entc.addressing.DAController;

import java.util.HashMap;
import java.util.ArrayList;
/**
 *
 * @author Gatta
 */
public class Types {
    HashMap<String, ArrayList> m = new HashMap<String, ArrayList>();
    public Types(){
        ArrayList list = new ArrayList();
        list.add("name");
        list.add("age");
        list.add("gn-division");
        m.put("road", list);
        list.add("area");
        list.add("tel");
        m.put("home", list);
        list.remove("area");
        list.add("business-type");
        m.put("Business", list);
        
    }
    
    public ArrayList getType(String key){
        return (m.get(key));
    }
    
    public HashMap getTypes(){
        return m;
    }
    
}
