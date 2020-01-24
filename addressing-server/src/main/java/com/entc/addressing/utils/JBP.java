package com.entc.addressing.utils;

import com.google.gson.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;

@Produces({"application/json1"})
@Consumes({"application/json1"})
@Provider
public class JBP implements MessageBodyWriter<Object>, MessageBodyReader<Object> {
    public JBP() {

    }

    public boolean isReadable(Class<?> type,
                              Type genericType,
                              Annotation[] annotations,
                              MediaType mediaType) {
        return (Map.class.isAssignableFrom(type) ||
                List.class.isAssignableFrom(type)) &&
                mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
    }

    public Object readFrom(Class<Object> type,
                           Type genericType,
                           Annotation[] annotations,
                           MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders,
                           InputStream entityStream) throws IOException, WebApplicationException {
        byte[] bytes = this.readData(entityStream);
        String jsonString = new String(bytes);
        //System.out.println(jsonString);
        return parseElement((new JsonParser()).parse(jsonString));
    }

    private byte[] readData(InputStream entityStream) throws IOException {
        boolean read = false;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int read1;
        while ((read1 = entityStream.read()) != -1) {
            baos.write(read1);
        }

        return baos.toByteArray();
    }

    public static Object parseElement(JsonElement element) {
        Object result = null;

        //System.out.println(element);
        Iterator i$;
        if (element.isJsonArray()) {
            ArrayList map = new ArrayList();
            JsonArray jsonObject = element.getAsJsonArray();
            i$ = jsonObject.iterator();

            while (i$.hasNext()) {
                JsonElement entry = (JsonElement) i$.next();
                map.add(parseElement(entry));
            }

            result = map;
        }
        else if (element.isJsonNull()) {
        }
        else if (element.isJsonPrimitive()) {
            result = element.getAsString();
            //System.out.println(element.getAsString());
        }
        else if (element.isJsonObject()) {

            HashMap map1 = new HashMap();

            JsonObject jsonObject1 = element.getAsJsonObject();
            //System.out.println(jsonObject1);
            //System.out.println("!!!!!!!!!!!!!!");

            i$ = jsonObject1.entrySet().iterator();

            while (i$.hasNext()) {
                //System.out.println(i$.next());
                Map.Entry entry1 = (Map.Entry) i$.next();
                //System.out.println(entry1);
                //System.out.println("&&&&&&&&&&&&&&");
                map1.put(entry1.getKey(), parseElement((JsonElement) entry1.getValue()));
                //System.out.println("@@@@@@@@@@@@@@@");
                //System.out.println(map1);
                //System.out.println("$$$$$$$$$$$$$$$");

            }

            result = map1;
            //System.out.println(map1);
        }
        //System.out.println(i);
        //System.out.println("sssssssssssssssss");
        //System.out.println(result);
        //System.out.println("jjjjjjjjjjjjjjjjjj");
        return result;

    }

    public boolean isWriteable(Class<?> type,
                               Type genericType,
                               Annotation[] annotations,
                               MediaType mediaType) {
        if ((Map.class.isAssignableFrom(type) ||
                List.class.isAssignableFrom(type)) &&
                mediaType.equals(MediaType.APPLICATION_JSON_TYPE)) {
            return true;
        } else {
            return false;
        }
    }

    public long getSize(Object message,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType) {
        return -1L;
    }

    public void writeTo(Object message,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException, WebApplicationException {
        entityStream.write((new Gson()).toJson(message).getBytes());
    }
}
