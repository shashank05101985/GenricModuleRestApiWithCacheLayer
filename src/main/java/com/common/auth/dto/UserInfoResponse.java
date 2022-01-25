package com.common.auth.dto;

import java.util.Collection;

public class UserInfoResponse {

    private final int id;
    private final String username;
    private final String email;
    private final String accessToken;
    private final Collection<String> roles;

    public UserInfoResponse(int id, String username, String email, Collection<String> roles,String accessToken) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.accessToken = accessToken;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public Collection<String> getRoles() {
        return roles;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
