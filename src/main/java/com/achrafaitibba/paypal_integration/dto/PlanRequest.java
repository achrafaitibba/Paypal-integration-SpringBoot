package com.achrafaitibba.paypal_integration.dto;

public record PlanRequest(
        /**
         * Sample request Body,
         * user will pay 303 every 6 months in the example below
         * {
         *     "productId": "PROD-99F99999G9999999V",
         *     "planName": "Test product name",
         *     "sequence" : 1,
         *     "intervalUnit" : "MONTH",
         *     "intervalCount" : 6,
         *     "planType": "REGULAR",
         *     "price": 303
         * }
         */
        //https://developer.paypal.com/docs/api/subscriptions/v1/#plans_create
        String productId,
        String planName,
        //int sequence,
        String intervalUnit,
        int  intervalCount,
        String planType,
        double price
) {
 }

