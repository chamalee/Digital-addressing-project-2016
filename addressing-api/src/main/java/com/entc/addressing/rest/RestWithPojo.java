package com.entc.addressing.rest;

import com.entc.addressing.AddressingWebClient;
import com.entc.addressing.pojo.AddressingResponse;
import com.entc.addressing.pojo.UserRegRequest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

/**
 * Authored by dushanp@hsenidmobile.com on 11/21/15.
 */
@Path("/api")
public class RestWithPojo {

    public RestWithPojo() {
    }

    @POST
    @Path("/service")
    @Produces("application/json")
    public AddressingResponse registerUser(UserRegRequest request) {
//        do what ever the thing you want here,

        AddressingWebClient client = new AddressingWebClient("http://www.mocky.io/v2/565185b111000073199e10cb");
        HashMap<Object, Object> reqToExternal = new HashMap<>();
        reqToExternal.put("userId", "3");
        Object postResp = client.post(reqToExternal, Map.class);

        String statusDesc = "User [" + request.getUsername() + "] has been created successfully";
        return new AddressingResponse("S1000", statusDesc, postResp);
    }
}
