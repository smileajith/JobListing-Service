package com.ajith.joblisting.JobListing.model;

public class UserResponse {

    private User user;

    public UserResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }

    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
