package com.uvic.model;

import org.h2.engine.User;

public class UserDetails {
    private String username;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDetails() { }

    public UserDetails(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
