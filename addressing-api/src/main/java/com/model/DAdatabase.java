package com.model;

//import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "digitaladdressdb")

public class DAdatabase {



    private Double lat;
    private Double lon;


    private HashMap<String, String>  attributes;

    private String type;


    private int reliability;

    private String digitalAddress1;
    private String digitalAddress2;
    private String digitalAddress3;

    public double getlat(){
        return lat;
    }
    public double getlon(){
        return lon;
    }
    public HashMap<String, String> getAttributes(){
        return attributes;
    }
    public String getType(){
        return type;
    }
    public int getreliability(){
        return  reliability;
    }

    public String getDigitalAddress1(){
        return digitalAddress1;
    }
    public String getDigitalAddress2(){
        return digitalAddress2;
    }
    public String getDigitalAddress3(){
        return digitalAddress3;
    }



    public void setLat(Double lat){this.lat=lat;}
    public void setLon(Double lon){this.lon=lon;}
    public void setAttributes(HashMap<String, String>  attributes){this.attributes=attributes;}
    public void addAttributes(String key, String value){attributes.put(key, value);
    }
    public void setType(String type){this.type=type;}
    public void setReliability(int reliability){this.reliability=reliability;}
    public void setDigitalAddress1(String digitalAddress1){this.digitalAddress1=digitalAddress1;}
    public void setDigitalAddress2(String digitalAddress2){this.digitalAddress2=digitalAddress2;}
    public void setDigitalAddress3(String digitalAddress3){this.digitalAddress3=digitalAddress3;}
    public String getAttribute(String key){
        return attributes.get(key);
    }
    public void updateAttribute(String key, String value){
        attributes.replace(key, value);
    }


    public DAdatabase(Double lat,Double lon,HashMap<String, String> attributes,String type, int reliability,String digitalAddress1,String digitalAddress2,String digitalAddress3){
        super();

        this.lat=lat;
        this.lon=lon;
        this.attributes=attributes;
        this.reliability=reliability;
        this.type=type;
        this.digitalAddress1=digitalAddress1;
        this.digitalAddress2=digitalAddress2;
        this.digitalAddress3=digitalAddress3;


    }

    @Override
    public String toString() {
        return "DA [lat=" + lat + ",lon=" + lon + ",attributes=" + attributes + ",type="+type+",reliability="+reliability+",DAtag1="+digitalAddress1+",DAtag2="+digitalAddress2+",DAtag3="+digitalAddress3+"]";
    }
}
