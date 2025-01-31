package com.achrafaitibba.paypal_integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FixedPrice {
    @JsonProperty("currency_code")
    private String currencyCode;
    private String value;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
