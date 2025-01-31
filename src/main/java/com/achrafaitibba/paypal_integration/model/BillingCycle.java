package com.achrafaitibba.paypal_integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BillingCycle {
    @JsonProperty("pricing_scheme")
    private PricingScheme pricingScheme;
    private Frequency frequency;
    @JsonProperty("tenure_type")
    private String tenureType;
    private int sequence;
    @JsonProperty("total_cycles")
    private int totalCycles;

    public PricingScheme getPricingScheme() {
        return pricingScheme;
    }

    public void setPricingScheme(PricingScheme pricingScheme) {
        this.pricingScheme = pricingScheme;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getTenureType() {
        return tenureType;
    }

    public void setTenureType(String tenureType) {
        this.tenureType = tenureType;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getTotalCycles() {
        return totalCycles;
    }

    public void setTotalCycles(int totalCycles) {
        this.totalCycles = totalCycles;
    }
}
