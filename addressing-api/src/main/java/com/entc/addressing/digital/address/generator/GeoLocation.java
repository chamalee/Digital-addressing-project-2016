/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entc.addressing.digital.address.generator;

/**
 *
 * @author Gatta
 */
public class GeoLocation {
    private double latitude;
    private double longtitude;
    
    public GeoLocation(){
        this.latitude = 0;
        this.longtitude = 0;
    }
    
    public GeoLocation(double latitude, double longtitude){
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
    
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    
    public void setLongtitude(double longtitude){
        this.longtitude = longtitude;
    }
    
    public double getLatitude(){
        return this.latitude;
    }
    
    public double getLongtitude(){
        return this.longtitude;
    }
}
