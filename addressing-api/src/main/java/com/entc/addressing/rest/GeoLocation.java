package com.entc.addressing.rest;

/**
 * Created by user on 10/31/2015.
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
