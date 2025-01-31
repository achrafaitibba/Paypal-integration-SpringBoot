package com.achrafaitibba.paypal_integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authentication {
    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
