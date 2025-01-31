package com.achrafaitibba.paypal_integration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PricingScheme {
    private int version;
    @JsonProperty("fixed_price")
    private FixedPrice fixedPrice;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private String createTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private String updateTime;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public FixedPrice getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(FixedPrice fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
