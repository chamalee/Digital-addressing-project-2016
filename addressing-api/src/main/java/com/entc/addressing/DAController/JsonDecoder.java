/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entc.addressing.DAController;

import java.util.HashMap;
/**
 *
 * @author Gatta
 */
public class JsonDecoder {
    
    public JsonDecodeObject decodeJson(HashMap jsonMap){
        int requestType = Integer.valueOf((String)jsonMap.get("rType"));
        if (requestType == 1){
        JsonDecodePosition temp = new JsonDecodePosition();
        temp.setLatitude(Double.valueOf((String)jsonMap.get("latitude")));
        temp.setLongtitude(Double.valueOf((String)jsonMap.get("longtitude")));
        temp.setType((String)jsonMap.get("type"));
        temp.setUser((String)jsonMap.get("user"));
        temp.setAttributes((HashMap)jsonMap.get("attributes"));
        return temp;    
        }
        return null;
    }
    
    
    
    
    
}
