package com.entc.addressing.rest;

import com.core.DBConnection;
import com.model.DAdatabase;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedHashMap;
import java.util.Map;

@XmlRootElement
public class SearchDARequest {

    private String digitalAddress1;
    private String digitalAddress2;
    private String digitalAddress3;
    private String rType;


    public SearchDARequest( String rType,String digitalAddress1,String digitalAddress2,String digitalAddress3) {
        this.rType=rType;
        this.digitalAddress1 = digitalAddress1;
        this.digitalAddress2 = digitalAddress2;
        this.digitalAddress3 = digitalAddress3;
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

        DBConnection dbc= new DBConnection();
       DAdatabase d= dbc.searchDA("digitalAddress1", digitalAddress1,"digitalAddress2", digitalAddress2,"digitalAddress3", digitalAddress3);
        //dbc.insert(new DAdatabase(DA.getLocation().getLatitude(),DA.getLocation().getLongtitude(),"attributes","type","des","reliability",DA.getDigitalAddresTag1(),DA.getDigitalAddressTag2(),DA.getDigitalAddressTag3()));



        return "latitude is " +
                d.getlat() +
                ". longitude is " +
                d.getlon() +
                "Digita Address is"+
                d.getAttributes().toString();
    }

}
