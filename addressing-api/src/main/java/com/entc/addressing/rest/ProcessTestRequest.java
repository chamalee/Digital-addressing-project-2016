package com.entc.addressing.rest;


import com.core.DBConnection;
import com.model.DAdatabase;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashMap;
import java.util.Map;

@XmlRootElement
public class ProcessTestRequest {



    private String lat;
    private String lon;


    public ProcessTestRequest(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Map<String, String> toResp () {
        Map<String, String> resp = new LinkedHashMap<>();
        resp.put("status", "SUCCESS");
        resp.put("description", "Request processed successfully");
        resp.put("result", createResult());
        return resp;
    }

    private String createResult() {
        //  Logic logic = new Logic(); // new DPoint Logic object
        //double[] gaps = logic.getDistance(Double.parseDouble(lat), Double.parseDouble(lon)); // find gaps by lat, lon

        //String DA= logic.getDA(gaps[0], gaps[1]);
        GeoLocation p = new GeoLocation(Double.parseDouble(lat), Double.parseDouble(lon));
        DigitalAddressGenerator generator = new DigitalAddressGenerator(p);
        DigitalAddress DA = generator.generateDigitalAddress();
        System.out.println(DA.getDigitalAddress());
        DBConnection dbc= new DBConnection();
        dbc.insert(new DAdatabase(DA.getLocation().getLatitude(),DA.getLocation().getLongtitude(),DA.getAttributes(),"type",5,DA.getDigitalAddresTag1(),DA.getDigitalAddressTag2(),DA.getDigitalAddressTag3()));



        return "latitude is " +
                lat +
                ". longitude is " +
                lon +
                "Digita Address is"+
                DA.getDigitalAddress();
    }

}
