package com.entc.addressing.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Authored by dushanp@hsenidmobile.com on 11/21/15.
 */
@XmlRootElement
public class UserRegRequest {
    private String username;

    private String password;

    public UserRegRequest() {
    }

    public UserRegRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append("UserRegRequest [");
        response.append("username=");
        response.append(username);
        response.append(", password=");
        response.append(password);
        return response.toString();
    }
}
