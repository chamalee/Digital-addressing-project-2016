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
public abstract class JsonDecodeObject {
    
    abstract public double getLatitude();
    
    abstract double getLongtitude();
    
    abstract public String getType();
    
    abstract public String getUser();
    
    abstract public HashMap<String, String> getAttributes();
    
    abstract public void setLatitude(double latitude);
    
    abstract public void setLongtitude(double longtitude);
    
    abstract public void setType(String type);
    
    abstract public void setUser(String user);
    
    abstract public void setAttributes(HashMap<String, String> attributes);
    
}
