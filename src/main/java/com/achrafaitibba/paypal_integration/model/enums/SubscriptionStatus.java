package com.achrafaitibba.paypal_integration.model.enums;

public enum SubscriptionStatus {
    APPROVAL_PENDING,//The subscription url is created but not yet approved by the buyer.
    APPROVED,//The buyer has approved the subscription.
    ACTIVE,//The subscription is active.
    SUSPENDED,//The subscription is suspended.
    CANCELLED,//The subscription is cancelled.
    EXPIRED
}

