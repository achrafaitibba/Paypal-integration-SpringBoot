package com.achrafaitibba.paypal_integration.model;


import com.achrafaitibba.paypal_integration.model.enums.SubscriptionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class Subscription {
    @JsonProperty("id")
    private String id;
    @JsonProperty("status")
    private SubscriptionStatus status;
    @JsonProperty("status_update_time")
    private String statusUpdateTime;
    @JsonProperty("plan_id")
    private String planId;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("subscriber")
    private Subscriber subscriber;
    @JsonProperty("create_time")
    private String createTime;
    @JsonProperty("links")
    private List<Link> links ;

    public Subscription(String id, SubscriptionStatus status, String statusUpdateTime, String planId, String startTime, Subscriber subscriber, String createTime, List<Link> links) {
        this.id = id;
        this.status = status;
        this.statusUpdateTime = statusUpdateTime;
        this.planId = planId;
        this.startTime = startTime;
        this.subscriber = subscriber;
        this.createTime = createTime;
        this.links = links;
    }

    public Subscription() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public String getStatusUpdateTime() {
        return statusUpdateTime;
    }

    public void setStatusUpdateTime(String statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}