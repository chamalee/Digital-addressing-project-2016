package com.entc.addressing;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Authored by dushanp@hsenidmobile.com on 11/22/15.
 */
public class AddressingWebClient {
    private WebClient webClient;

    public AddressingWebClient(String url) {
        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJaxbJsonProvider());

        WebClient client = WebClient.create(url, providers);
        client = client.accept("application/json").type("application/json");
        this.webClient = client;
    }

    public Object post(Object request,Class entity) {
        return webClient.post(request, entity);
    }
}
