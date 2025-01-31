package com.achrafaitibba.paypal_integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
}
