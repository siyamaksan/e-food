package com.example.demo.service.telegram;

import com.example.demo.dto.form.SendMessageRequest;
import com.example.demo.dto.telegram.TelegramResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class TelegramClientFallbackFactory implements FallbackFactory<TelegramClient> {

    @Override
    public TelegramClient create(Throwable cause) {
        return new TelegramClient() {
            @Override
            public TelegramResponse getUpdates(Long offset, Integer timeout) {
                log.error("Telegram API call failed. Reason: {}", cause.getMessage());

                TelegramResponse response = new TelegramResponse();
                response.setOk(false);
                response.setResult(Collections.emptyList());
                return response;            }

            @Override
            public TelegramResponse sendMessage(SendMessageRequest request) {
                TelegramResponse response = new TelegramResponse();
                response.setOk(false);
                return response;
            }
        };
    }
}