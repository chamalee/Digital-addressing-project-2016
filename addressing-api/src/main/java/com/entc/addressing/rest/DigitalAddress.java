package com.entc.addressing.rest;

import java.util.HashMap;

/**
 * Created by user on 10/31/2015.
 */
public class DigitalAddress {

    private String digitalAddressTag1;
    private String digitalAddressTag2;
    private String digitalAddressTag3;
    private GeoLocation location;
    private String type;
    private int reliability;
    public DigitalAddress(){
        this.location = new GeoLocation();
    }

    public DigitalAddress(GeoLocation location){
        this.location = new GeoLocation(location.getLatitude(), location.getLongtitude());
    }

    public HashMap<String, String> getAttributes() {
        return  getAttributes();
    }
    public String getType(){return type;}

    public  void setType(){this.type=type;}
    public void setDigitalAddressTag1(String digitalAddressTag1){
        this.digitalAddressTag1 = digitalAddressTag1;
    }

    public void setDigitalAddressTag2(String digitalAddressTag2){
        this.digitalAddressTag2 = digitalAddressTag2;
    }

    public void setDigitalAddressTag3(String digitalAddressTag3){
        this.digitalAddressTag3 = digitalAddressTag3;
    }

    public String getDigitalAddresTag1(){
        return this.digitalAddressTag1;
    }

    public String getDigitalAddressTag2(){
        return this.digitalAddressTag2;
    }

    public String getDigitalAddressTag3(){
        return this.digitalAddressTag3;
    }

    public void setLoacation(float latitude, float longtitude){
        this.location = new GeoLocation(latitude, longtitude);
    }

    public void setLocationLatitude(float latitude){
        this.location.setLatitude(latitude);
    }

    public void setLocationLongtitude(float longtitude){
        this.location.setLongtitude(longtitude);
    }

    public GeoLocation getLocation(){
        return this.location;
    }




    public String getDigitalAddress(){
        String digitalAddress;
        digitalAddress = this.digitalAddressTag1 + "-" + this.digitalAddressTag2 + "-" + this.digitalAddressTag3;
        return digitalAddress;
    }



}
