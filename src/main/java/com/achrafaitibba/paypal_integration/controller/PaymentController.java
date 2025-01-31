package com.achrafaitibba.paypal_integration.controller;


import com.achrafaitibba.paypal_integration.dto.CreateSubscriptionRequest;
import com.achrafaitibba.paypal_integration.dto.PlanRequest;
import com.achrafaitibba.paypal_integration.dto.ReviseSubscriptionRequest;
import com.achrafaitibba.paypal_integration.model.PaypalPlan;
import com.achrafaitibba.paypal_integration.model.Product;
import com.achrafaitibba.paypal_integration.model.Subscription;
import com.achrafaitibba.paypal_integration.service.SubscriptionService;


public class PaymentController {

    private final SubscriptionService subscriptionService;

    public PaymentController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * To create a subscription you should have a product, product_id required
     * Then you should create a plan/plans with the product Id,
     * Using the plan_id you can are now able to create a subscription hh
     * https://developer.paypal.com/docs/api/catalog-products/v1/#products_create
     */
    public Product createProduct(String productName) throws Exception{
        return subscriptionService.createProduct(productName);
    }

    // https://developer.paypal.com/docs/api/subscriptions/v1/#plans_create
    public PaypalPlan createPlan(PlanRequest request) throws Exception{
        return subscriptionService.createPlan(request);
    }

    // https://developer.paypal.com/docs/api/subscriptions/v1/#subscriptions_create
    public Subscription createSubscription(CreateSubscriptionRequest request) throws Exception {
        return subscriptionService.createSubscription(request);
    }

    // https://developer.paypal.com/docs/api/subscriptions/v1/#subscriptions_get
    // you can check the subscription status to give access to user with ACTIVE subs, and get user infos...
    public Subscription getSubscriptionDetails(String subscriptionId) throws Exception {
        return subscriptionService.getSubscription(subscriptionId);
    }

    // Subscription could be upgraded using reviseSubscription
    public Object reviseSubscription(ReviseSubscriptionRequest request) throws Exception {
        return subscriptionService.reviseSubscription(request);
    }

    public int cancelSubscription(String subscriptionId, String raison) throws Exception {
        return subscriptionService.cancelSubscription(subscriptionId, raison);
    }

//    public String approveSubscriptionUrl(String subsId) throws Exception {
//        return subscriptionService.approveSubscription(subsId);
//    }


//    public Object upgradeSubscription(ReviseSubscriptionRequest request) throws Exception {
//        return subscriptionService.upgradeSubscription(request);
//    }
}