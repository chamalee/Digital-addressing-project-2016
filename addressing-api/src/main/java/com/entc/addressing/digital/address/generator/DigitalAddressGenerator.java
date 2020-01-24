/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entc.addressing.digital.address.generator;


public class DigitalAddressGenerator {
    private DigitalAddress DA;
    private final GeoLocation p1 = new GeoLocation(9.579372, 79.612397);         // left most point
    private final GeoLocation p2 = new GeoLocation();         // right most point
    private final GeoLocation p3 = new GeoLocation(9.843050, 80.208777);         // top most point
    private final GeoLocation p4 = new GeoLocation();         // lowest point
    private final int columns = 16;
    private final int rows = 88;
    private final int secondaryDivisions = 100;
    private final int tag3Columns = 16;
    private final int tag3Rows = 10;
    
    public DigitalAddressGenerator(){
        
    }
    
    public DigitalAddressGenerator(GeoLocation geoPoint){
        this.DA = new DigitalAddress(geoPoint);
    }
    
    public DigitalAddress generateDigitalAddress(){
        GeoLocation p = generateTag1();
        
        p = generateTag2(p);
        System.out.println(p.getLatitude());
        System.out.println(p.getLongtitude());
        p = generateTag3(p);
        calculateReliability(p);
        
        
        return DA;
    }

    public GeoLocation generateTag1(){
        //double gridWidth = getVerticalDistance(p1, p2);
        //double gridLength = getHorizontalDistance(p3,p4);
       // int vdivisions = (int) (gridWidth / columns);
       // int hdivisions = (int) (gridLength / rows);
        int vdivisions = 16;
        int hdivisions = 5;
        int vcode = 65 + (int) (getVerticalDistance(p1, DA.getLocation()) / vdivisions);
        int hcode = 1 + (int) (getHorizontalDistance(p3, DA.getLocation()) / hdivisions);
        char c = (char) vcode;
        String tag1 = String.valueOf(c) + String.valueOf(hcode);
        DA.setDigitalAddressTag1(tag1);
        double vdistance = (vcode - 65) * vdivisions;
        double hdistance = (hcode - 1) * hdivisions;
        //System.out.println(hdistance);
        
        return new GeoLocation(calculateLatitude(p3, hdistance), calculateLongtitude(p1, vdistance));
        
    }
    
    public GeoLocation generateTag2(GeoLocation p){
        double blockWidth = 16;
        double blockHeight = 5;
        
        double vdivisions =  (blockWidth / secondaryDivisions);
        double hdivisions =  (blockHeight / secondaryDivisions);
        int vcode = 0 + (int) (getVerticalDistance(p, DA.getLocation()) / vdivisions);
        int hcode = 0 + (int) (getHorizontalDistance(p, DA.getLocation()) / hdivisions);
        String tag1 = String.valueOf(vcode) + String.valueOf(hcode);
        DA.setDigitalAddressTag2(tag1);
        double vdistance = vcode * vdivisions;
        double hdistance = hcode * hdivisions;
        return new GeoLocation(calculateLatitude(p, hdistance), calculateLongtitude(p, vdistance));
        
    }
    
    public GeoLocation generateTag3(GeoLocation p){
        double blockWidth = .16;
        double blockHeight = .05;
        double vdivisions =  (blockWidth / tag3Columns);
        double hdivisions =  (blockHeight / tag3Rows);
        int vcode = 70 + (int) (getVerticalDistance(p, DA.getLocation()) / vdivisions);
        int hcode = 0 + (int) (getHorizontalDistance(p, DA.getLocation()) / hdivisions);
        char c = (char) vcode;
        String tag3 = String.valueOf(c) + String.valueOf(hcode);
        DA.setDigitalAddressTag3(tag3);
        double vdistance = vcode * vdivisions;
        double hdistance = hcode * hdivisions;
        return new GeoLocation(calculateLatitude(p, hdistance), calculateLongtitude(p, vdistance));
        
    }
    
    public double getVerticalDistance(GeoLocation p1, GeoLocation p2){
        double distance = Haversine.distance(p1.getLatitude(), p1.getLongtitude(), p1.getLatitude(), p2.getLongtitude());
        return distance;
    }
    
    public double getHorizontalDistance(GeoLocation p1, GeoLocation p2){
        double distance = Haversine.distance(p1.getLatitude(), p1.getLongtitude(), p2.getLatitude(), p1.getLongtitude());
        return distance;
    }
    
    public double calculateLatitude(GeoLocation p, double distance){
        double latitude = 0;
        GeoLocation temp = fromBearingDistance(p.getLatitude(), p.getLongtitude(), Math.toRadians(180), distance);
        latitude = temp.getLatitude();
        return latitude;
    }
    
    public double calculateLongtitude(GeoLocation p, double distance){
        double longtitude = 0;
        GeoLocation temp = fromBearingDistance(p.getLatitude(), p.getLongtitude(), Math.toRadians(90),distance);
        longtitude = temp.getLongtitude();
        return longtitude;
    }
    
    public GeoLocation fromBearingDistance(double lat1, double lon1, double brng, double d) {     
    double R = 6371.0;     
    double SLat  = Math.toRadians((lat1));
    double SLong = Math.toRadians((lon1));
    double lat2 = Math.asin( Math.sin(SLat)*Math.cos(d/R) + 
              Math.cos(SLat)*Math.sin(d/R)*Math.cos(brng) );
    double lon2 = SLong + Math.atan2(Math.sin(brng)*Math.sin(d/R)*Math.cos(SLat), 
                     Math.cos(d/R)-Math.sin(SLat)*Math.sin(lat2));
    double dlat2 = Math.toDegrees(lat2);
    //System.out.println(dlat2);
    double dlng2 = Math.toDegrees(lon2);
    return new GeoLocation(dlat2,dlng2);   
    }
    
    public void calculateReliability(GeoLocation p){
        Double reliabilityIndex;
        double hDistance = getHorizontalDistance(p, DA.getLocation());
        double vDistance = getVerticalDistance(p, DA.getLocation());
        if (vDistance > 5.0)
            vDistance = 10.0 - vDistance;
        if (hDistance > 2.5)
            hDistance = 5.0 - hDistance;
        
        hDistance = hDistance / 2.5;
        vDistance = vDistance / 5.0;
        
        if (vDistance < hDistance)
            reliabilityIndex = vDistance;
        else
            reliabilityIndex = hDistance;
        DA.setReliability((int)(reliabilityIndex * 100));
                
    }


    public GeoLocation[] getPolygon(String[] daTags){

        if (daTags.length == 3){
            double blockWidth = .16;
            double blockHeight = .05;
            double vdivisions =  (blockWidth / tag3Columns);
            double hdivisions =  (blockHeight / tag3Rows);
            String [] temp = new String[2];
            temp [0] = daTags[0];
            temp[1] = daTags[1];
            GeoLocation[] polygon = getPolygon(temp);
            int vCode = daTags[2].charAt(0);
            int hCode = Integer.valueOf(daTags[2].substring(1));
            double vdistance = vCode * vdivisions;
            double hdistance = hCode * hdivisions;
            GeoLocation[] polygon2 = new GeoLocation[4];
            polygon2[0] = new GeoLocation();
            polygon2[1] = new GeoLocation();
            polygon2[2] = new GeoLocation();
            polygon2[3] = new GeoLocation();

            polygon2[0].setLatitude(calculateLatitude(polygon[0], hdistance));
            polygon2[0].setLongtitude(calculateLongtitude(polygon[0], vdistance));
            polygon2[1] = fromBearingDistance(polygon2[0].getLatitude(), polygon2[0].getLongtitude(), Math.toRadians(90), vdivisions);
            polygon2[2] = fromBearingDistance(polygon2[1].getLatitude(), polygon2[1].getLongtitude(), Math.toRadians(0), hdivisions);
            polygon2[3] = fromBearingDistance(polygon2[0].getLatitude(), polygon2[0].getLongtitude(), Math.toRadians(0), hdivisions);
            return polygon2;


        }
        else if (daTags.length == 2){
            double blockWidth = 16.0;
            double blockHeight = 5.0;

            double vdivisions =  (blockWidth / secondaryDivisions);
            double hdivisions =  (blockHeight / secondaryDivisions);

            String [] temp = new String [1];
            temp[0] = daTags[0];
            GeoLocation[] polygon = getPolygon(temp);
            int vCode = Integer.valueOf(daTags[1].substring(0, 2));
            int hCode = Integer.valueOf(daTags[1].substring(2,4));
            double vdistance = vCode * vdivisions;
            double hdistance = hCode * hdivisions;
            GeoLocation[] polygon2 = new GeoLocation[4];
            polygon2[0] = new GeoLocation();
            polygon2[1] = new GeoLocation();
            polygon2[2] = new GeoLocation();
            polygon2[3] = new GeoLocation();
            System.out.println(hCode);

            polygon2[0].setLatitude(calculateLatitude(polygon[0], hdistance));
            polygon2[0].setLongtitude(calculateLongtitude(polygon[0], vdistance));
            polygon2[1] = fromBearingDistance(polygon2[0].getLatitude(), polygon2[0].getLongtitude(), Math.toRadians(90), vdivisions);
            polygon2[2] = fromBearingDistance(polygon2[1].getLatitude(), polygon2[1].getLongtitude(), Math.toRadians(0), hdivisions);
            polygon2[3] = fromBearingDistance(polygon2[0].getLatitude(), polygon2[0].getLongtitude(), Math.toRadians(0), hdivisions);
            System.out.println(polygon2[0].getLatitude());
            System.out.println(polygon2[0].getLongtitude());
            return polygon2;

        }
        else if (daTags.length == 1){
            int vdivisions = 16;
            int hdivisions = 5;
            int vCode = daTags[0].charAt(0);
            int hCode = Integer.valueOf(daTags[0].substring(1));


            double vdistance = (vCode - 65) * vdivisions;
            double hdistance = (hCode - 1) * hdivisions;
            GeoLocation[] polygon = new GeoLocation[4];
            polygon[0] = new GeoLocation();
            polygon[1] = new GeoLocation();
            polygon[2] = new GeoLocation();
            polygon[3] = new GeoLocation();
            //System.out.println(hCode);
            polygon[0].setLatitude(calculateLatitude(p3, hdistance));
            //System.out.println("awaaaaa");
            polygon[0].setLongtitude(calculateLongtitude(p1, vdistance));
            polygon[1] = fromBearingDistance(polygon[0].getLatitude(), polygon[0].getLongtitude(), Math.toRadians(90), 16.0);
            polygon[2] = fromBearingDistance(polygon[1].getLatitude(), polygon[1].getLongtitude(), Math.toRadians(0), 5.0);
            polygon[3] = fromBearingDistance(polygon[0].getLatitude(), polygon[0].getLongtitude(), Math.toRadians(0), 5.0);

            return polygon;





        }
        return null;
    }
    
}

