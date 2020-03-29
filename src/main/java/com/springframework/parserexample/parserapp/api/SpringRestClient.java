package com.springframework.parserexample.parserapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class SpringRestClient {

    @Autowired
    RestTemplate restTemplate;

    public String getProductList(String url) {
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, "SpringSource");
        return result;
    }
}
