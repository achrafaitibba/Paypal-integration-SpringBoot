package com.achrafaitibba.paypal_integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Frequency {
    @JsonProperty("interval_unit")
    private String intervalUnit;
    @JsonProperty("interval_count")
    private int intervalCount;

    public int getIntervalCount() {
        return intervalCount;
    }

    public void setIntervalCount(int intervalCount) {
        this.intervalCount = intervalCount;
    }

    public String getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(String intervalUnit) {
        this.intervalUnit = intervalUnit;
    }
}
