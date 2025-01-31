package com.achrafaitibba.paypal_integration.service;


import com.achrafaitibba.paypal_integration.dto.CreateSubscriptionRequest;
import com.achrafaitibba.paypal_integration.dto.PlanRequest;
import com.achrafaitibba.paypal_integration.dto.ReviseSubscriptionRequest;
import com.achrafaitibba.paypal_integration.model.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SubscriptionService {
    private final PaypalAuthenticationService authenticationService;
    private final HttpHeaders httpHeaders;
    private final RestTemplate restTemplate;
    private final BaseUrl baseUrl;

    public SubscriptionService(PaypalAuthenticationService authenticationService, HttpHeaders httpHeaders, RestTemplate restTemplate, BaseUrl baseUrl) {
        this.authenticationService = authenticationService;
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    private void requestHeaders() throws Exception {
        httpHeaders.clear();
        String token = authenticationService.generateToken().getAccessToken();
        httpHeaders.add("Authorization", "Bearer " + token);
    }

    public Subscription createSubscription(CreateSubscriptionRequest subscription) throws Exception {
        requestHeaders();
        httpHeaders.add("Prefer", "return=representation");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("plan_id", subscription.planId());
        requestBody.put("start_time", subscription.startTime() + "T00:00:00Z");
        Map<String, String> applicationContext = new HashMap<>();
        applicationContext.put("return_url", subscription.returnUrl());
        applicationContext.put("cancel_url", subscription.cancelUrl());
        requestBody.put("application_context", applicationContext);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<Subscription> response = restTemplate.exchange(
                baseUrl.getBaseUrl() + "v1/billing/subscriptions",
                HttpMethod.POST,
                requestEntity,
                Subscription.class
        );
        return response.getBody();
    }

    public Subscription getSubscription(String subscriptionId) throws Exception {
        requestHeaders();
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Subscription> response = restTemplate.exchange(
                baseUrl.getBaseUrl() + "v1/billing/subscriptions/" + subscriptionId,
                HttpMethod.GET,
                requestEntity,
                Subscription.class
        );
        return response.getBody();
    }
//
//    public String approveSubscription(String subsId) throws Exception {
//        Subscription subscription = getSubscription(subsId);
//        return subscription.getLinks().stream()
//                .filter(link -> link.getRel().equals("approve"))
//                .findFirst()
//                .map(Link::getHref)
//                .orElse("");
//    }

    private Subscription updateSubscription(ReviseSubscriptionRequest request) throws Exception {
        requestHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("plan_id", request.planId());
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        restTemplate.postForEntity(
                baseUrl.getBaseUrl() + "v1/billing/subscriptions/".concat(request.subscriptionId()).concat("/revise"),
                requestEntity,
                Subscription.class
        );
        return getSubscription(request.subscriptionId());
    }

    public Object reviseSubscription(ReviseSubscriptionRequest request) throws Exception {
        // return isSubscriptionActive(request.subscriptionId()) ? updateSubscription(request) : new RequestException("The subscription is not ACTIVE yet", HttpStatus.CONFLICT).getMessage();
        return isSubscriptionActive(request.subscriptionId()) ? updateSubscription(request) : "The subscription is not ACTIVE yet";

    }

//    public Object upgradeSubscription(ReviseSubscriptionRequest request) throws Exception{
//        return updateSubscription(request);
//    }

    private Boolean isSubscriptionActive(String subscriptionId) throws Exception {
        return getSubscription(subscriptionId).getStatus().toString().equals("ACTIVE");
    }

    public Product createProduct(String productName) throws Exception{
        requestHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Prefer", "return=representation");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", productName);
        requestBody.put("type", "DIGITAL");
        requestBody.put("category", "SOFTWARE");
        requestBody.put("home_url","https://example.com");
        requestBody.put("description", "test product description");
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<Product> response = restTemplate.exchange(
                baseUrl.getBaseUrl() + "v1/catalogs/products",
                HttpMethod.POST,
                requestEntity,
                Product.class
        );
        return response.getBody();
    }

    public PaypalPlan createPlan(PlanRequest request) throws Exception {
        requestHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Prefer", "return=representation");
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("product_id", request.productId());
        requestBody.put("name", request.planName());
        List<BillingCycle> billingCycles = new ArrayList<>();
        BillingCycle billingCycle = new BillingCycle();
        PricingScheme pricingScheme = new PricingScheme();
        FixedPrice fixedPrice = new FixedPrice();
        fixedPrice.setCurrencyCode("USD");
        fixedPrice.setValue(String.valueOf(request.price()));
        pricingScheme.setFixedPrice(fixedPrice);
        Frequency frequency  = new Frequency();
        frequency.setIntervalCount(request.intervalCount());
        frequency.setIntervalUnit(request.intervalUnit());
        billingCycle.setFrequency(frequency);
        billingCycle.setTenureType(request.planType());
        billingCycle.setSequence(1);
        billingCycle.setPricingScheme(pricingScheme);
        billingCycles.add(billingCycle);
        requestBody.put("billing_cycles", billingCycles);
        requestBody.put("payment_preferences", new HashMap<>());
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<PaypalPlan> response = restTemplate.exchange(
                baseUrl.getBaseUrl() + "v1/billing/plans",
                HttpMethod.POST,
                requestEntity,
                PaypalPlan.class
        );
        return response.getBody();
    }

    public int cancelSubscription(String subscriptionId, String raison) throws Exception {
        requestHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("raison", raison);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<Void> response = restTemplate.exchange(
                baseUrl.getBaseUrl() + "v1/billing/subscriptions/" + subscriptionId + "/cancel",
                HttpMethod.POST,
                requestEntity,
                Void.class);
        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            return 1;
        } else {
            return 0;
        }
    }

}