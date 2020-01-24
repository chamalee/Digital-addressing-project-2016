package com.entc.addressing.rest;

import com.entc.addressing.DAController.DAController;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@Path("/test")
public class AddressingRestService {

    public AddressingRestService() {

    }

    @POST
    @Path("/service")
    @Produces("application/json")
    public Map<String, String> test(Map<String, String> request) {
        ProcessTestRequest result = new ProcessTestRequest(
                request.get("lat"),
                request.get("lon")

        );

        return result.toResp();
    }

    @POST
    @Path("/registerda")
    @Produces("application/json")
    public HashMap registerDA(HashMap request){
        DAController controller = new DAController();
        return controller.registerDA(request);
    }

    @POST
    @Path("/searchda")
    @Produces("application/json")
    public HashMap searchDA(HashMap request){
        DAController controller = new DAController();
        return controller.searchDA(request);
    }

}
