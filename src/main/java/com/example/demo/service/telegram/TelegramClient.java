package com.example.demo.service.telegram;

import com.example.demo.config.FeignConfig;
import com.example.demo.dto.telegram.TelegramResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "telegramClient", url = "https://api.telegram.org/bot${telegram.bot.token}")
public interface TelegramClient {

    @GetMapping("/getUpdates")
    TelegramResponse getUpdates(
            @RequestParam(value = "offset", required = false) Long offset,
            @RequestParam(value = "timeout", required = false) Integer timeout
    );
}