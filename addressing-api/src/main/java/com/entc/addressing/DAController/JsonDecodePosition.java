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
public class JsonDecodePosition extends JsonDecodeObject {
    private double latitude;
    private double longtitude;
    private String type;
    private String user;
    private HashMap<String, String> attributes = new HashMap<>();
    
    
    @Override
    public double getLatitude(){
        return latitude;
    }
    
    @Override
    public double getLongtitude(){
        return longtitude;
    }
    
    @Override
    public String getType(){
        return type;
    }
    
    @Override
    public String getUser(){
        return user;
    }
    
    @Override
    public HashMap<String, String> getAttributes(){
        return attributes;
    }
    
    
    @Override
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    
    @Override
    public void setLongtitude(double longtitude){
        this.longtitude = longtitude;
    }
    
    @Override
    public void setType(String type){
        this.type = type;
    }
    
    @Override
    public void setUser(String user){
        this.user = user;
    }
    
    @Override
    public void setAttributes(HashMap<String, String> attributes){
        this.attributes = attributes;
    }
    
}
