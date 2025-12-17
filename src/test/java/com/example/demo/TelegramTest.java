package com.example.demo;

import com.example.demo.dto.telegram.TelegramResponse;
import com.example.demo.service.telegram.TelegramClient;
import com.example.demo.service.telegram.TelegramFeignPollingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class TelegramTest {

@Mock
    private TelegramClient telegramClient;

    @InjectMocks
    private TelegramFeignPollingService telegramFeignPollingService;

//    @InjectMocks
//    private OrderService orderService;

    private TelegramResponse getMessage() {
        String json = """
            {
              "ok": true,
              "result": [
                {
                  "update_id": 123456789,
                  "message": {
                    "message_id": 1,
                    "from": {
                      "id": 11111111,
                      "first_name": "Alice",
                      "username": "alice123"
                    },
                    "chat": {
                      "id": 11111111,
                      "first_name": "Alice",
                      "username": "alice123",
                      "type": "private"
                    },
                    "date": 1670000000,
                    "text": "1"
                  }
                }
              ]
            }
            """;

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, TelegramResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse test JSON", e);
        }
    }



}
