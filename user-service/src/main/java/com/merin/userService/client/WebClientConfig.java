package com.merin.userService.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean(name = "movieServiceClient")
    public WebClient employeeClient() {
        return WebClient.builder()
                .baseUrl("http://mvoie-service")
                .filter(filterFunction)
                .build();
    }
}
