package com.achrafaitibba.paypal_integration.dto;

public record CreateSubscriptionRequest(
        String planId,
        String startTime,
        String returnUrl,
        String cancelUrl
) {

}