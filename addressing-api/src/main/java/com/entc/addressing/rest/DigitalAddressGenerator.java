package com.entc.addressing.rest;

/**
 * Created by user on 10/31/2015.
 */
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
        generateTag3(p);


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

    public void generateTag3(GeoLocation p){
        double blockWidth = .16;
        double blockHeight = .05;
        double vdivisions =  (blockWidth / tag3Columns);
        double hdivisions =  (blockHeight / tag3Rows);
        int vcode = 70 + (int) (getVerticalDistance(p, DA.getLocation()) / vdivisions);
        int hcode = 0 + (int) (getHorizontalDistance(p, DA.getLocation()) / hdivisions);
        char c = (char) vcode;
        String tag3 = String.valueOf(c) + String.valueOf(hcode);
        DA.setDigitalAddressTag3(tag3);

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
        GeoLocation temp = fromBearingDistance(p.getLatitude(), p.getLongtitude(), Math.toRadians(180),distance);
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

}
