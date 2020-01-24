package com.model;

/**
 * Created by user on 11/8/2015.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "osmnewpoints")
public class osmpoints {
    private String id;
    private String name;
    private Double lat;
    private Double lon;
    private String highway;//traffic_signals

    public String getId(){return id;}
    public String getName(){return name;}
    public Double getLat(){return lat;}
    public Double getLon(){return lon;}
    public String getHighway(){return highway;}

    public void setId(String id){this.id=id;};
    public void setName(String name){this.name=name;}
    public void setLat(Double lat){this.lat=lat;}
    public  void setLon(Double lon){this.lon=lon;}

    public osmpoints(String id, String name, double lat,double lon,String  highway){}

}
