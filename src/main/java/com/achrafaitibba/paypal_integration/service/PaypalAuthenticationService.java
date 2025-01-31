package com.achrafaitibba.paypal_integration.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.achrafaitibba.paypal_integration.model.Authentication;
import com.achrafaitibba.paypal_integration.model.BaseUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class PaypalAuthenticationService {

    @Value("${payment.paypal.client-id}")
    private String clientId;
    @Value("${payment.paypal.secret}")
    private String secret;

    private final BaseUrl baseUrl;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    public PaypalAuthenticationService(BaseUrl baseUrl, ObjectMapper objectMapper, RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.baseUrl = baseUrl;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public HttpHeaders authenticate() {
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(clientId, secret);
        return httpHeaders;
    }

    public Authentication generateToken() throws Exception {
        LinkedMultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("ignoreCache", "true");
        requestBody.add("return_authn_schemes", "true");
        requestBody.add("return_client_metadata", "true");
        requestBody.add("return_unconsented_scopes", "true");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, authenticate());
        String response = restTemplate.postForObject(baseUrl.getBaseUrl() + "v1/oauth2/token", requestEntity, String.class);
        return objectMapper.readValue(response, Authentication.class);
    }


    //todo, terminate token after each request
    public void terminateToken(String authenticationToken) throws Exception {
        httpHeaders.add("Authorization", authenticationToken);
        LinkedMultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("token", authenticationToken);
        requestBody.add("token_type_hint", "ACCESS_TOKEN");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        restTemplate.exchange(baseUrl.getBaseUrl() + "v1/oauth2/token/terminate", HttpMethod.POST, requestEntity, String.class);
    }
}
