package com.vote.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserInfoWebClient {
    
    @Bean("userWebClient")
    public WebClient userInfoWebClient() {
        return WebClient.builder()
            .baseUrl("https://user-info.herokuapp.com/users/")
            .build();
    }
    
}
