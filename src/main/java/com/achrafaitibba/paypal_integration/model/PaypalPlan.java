package com.achrafaitibba.paypal_integration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaypalPlan {
    @JsonProperty("id")
    private String id;
    @JsonProperty("product_id")
    private String productId;
    private String name;
    private String status;
    @JsonProperty("billing_cycles")
    private List<BillingCycle> billingCycles;
    private boolean quantitySupported;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonProperty("create_time")
    private String createTime;
    @JsonProperty("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private String updateTime;
    private List<Link> links;
}

