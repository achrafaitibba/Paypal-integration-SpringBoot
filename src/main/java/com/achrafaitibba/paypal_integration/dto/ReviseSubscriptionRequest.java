package com.achrafaitibba.paypal_integration.dto;

public record ReviseSubscriptionRequest(
        String subscriptionId,
        String planId
) {
}
