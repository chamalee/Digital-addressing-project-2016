   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
   package com.entc.addressing.digital.address.generator;
   import java.util.HashMap;

public class DigitalAddress {
    private String digitalAddressTag1;
    private String digitalAddressTag2;
    private String digitalAddressTag3;
    private GeoLocation location;
    private String type;
    private int reliability;
    private  HashMap<String, String> attributes = new HashMap<String, String>();
    
    public DigitalAddress(){
        this.location = new GeoLocation();
    }
    
    public DigitalAddress(GeoLocation location){
        this.location = new GeoLocation(location.getLatitude(), location.getLongtitude());
    }

    public DigitalAddress(Double lat,Double lon,HashMap<String, String> attributes,String type, int reliability,String digitalAddress1,String digitalAddress2,String digitalAddress3){
        super();

        location.setLatitude(lat);
        location.setLongtitude(lon);
        this.attributes=attributes;
        this.reliability=reliability;
        this.type=type;
        this.digitalAddressTag1=digitalAddress1;
        this.digitalAddressTag2=digitalAddress2;
        this.digitalAddressTag3=digitalAddress3;


    }
    
    public void setDigitalAddressTag1(String digitalAddressTag1){
        this.digitalAddressTag1 = digitalAddressTag1;

    }
    public  void setAttributes(HashMap<String, String> attributes){this.attributes=attributes;}
    public void setDigitalAddressTag2(String digitalAddressTag2){
        this.digitalAddressTag2 = digitalAddressTag2;
    }
    public  void setType(String type){this.type=type;}
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


    
    public void setLoacation(double latitude, double longtitude){
        this.location = new GeoLocation(latitude, longtitude);
    }    
    
    public void setLocationLatitude(double latitude){
        this.location.setLatitude(latitude);
    }
    
    public void setLocationLongtitude(double longtitude){
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
    
    public void setReliability(int reliability){
        this.reliability = reliability;
    }
    
    public int getReliability(){
        return reliability;
    }
    public String getType(){return type;}
    
    public void addAttributes(String key, String value){
        attributes.put(key, value);
    }
    
    public HashMap<String, String> getAttributes(){
        return attributes;
    }
    
    public String getAttribute(String key){
        return attributes.get(key);
    }
    
    public void updateAttribute(String key, String value){
        attributes.replace(key, value);
    }
}
