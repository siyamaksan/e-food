package com.example.demo.config;

import com.example.demo.service.telegram.TelegramClient;
import feign.Contract;
import feign.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Value("${telegram.bot.token}")
    private String token;

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }
}