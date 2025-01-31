package com.achrafaitibba.paypal_integration.configuration;

import com.achrafaitibba.paypal_integration.model.BaseUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentConfig {

    @Value("${payment.paypal.api.base-url}")
    private String basUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Scope("prototype")
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }

    @Bean
    public BaseUrl baseUrl(){
        BaseUrl baseUrl = new BaseUrl();
        baseUrl.setBaseUrl(basUrl);
        return baseUrl;
    }

}