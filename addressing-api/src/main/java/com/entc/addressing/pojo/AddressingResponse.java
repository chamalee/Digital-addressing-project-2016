package com.entc.addressing.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Authored by dushanp@hsenidmobile.com on 11/21/15.
 */
@XmlRootElement
public class AddressingResponse {
    private String statusCode;

    private String statusDesc;

    private Object result;

    public AddressingResponse() {
    }

    public AddressingResponse(String statusCode, String statusDesc, Object result) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
        this.result = result;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append("Response [");
        response.append("statusCode=");
        response.append(statusCode);
        response.append(", statusDesc=");
        response.append(statusDesc);
        response.append(", result=");
        response.append(result);
        return response.toString();
    }
}
