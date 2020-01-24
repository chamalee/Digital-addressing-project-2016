package com.entc.addressing.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

@Path("/searchda")
public class SearchDAService {

    public SearchDAService(){}

    @POST
    @Path("/service")
    @Produces("application/json")
    public Map<String, String> test(Map<String, String> request) {
        SearchDARequest result = new SearchDARequest(
                request.get("rType"),
                request.get("digitalAddress1"),
                request.get("digitalAddress2"),
                request.get("digitalAddress3")
        );
        return result.toResp();
    }


}
