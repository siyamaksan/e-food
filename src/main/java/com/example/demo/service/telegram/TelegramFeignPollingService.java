package com.example.demo.service.telegram;

import com.example.demo.dto.form.CreateFoodDto;
import com.example.demo.dto.telegram.Message;
import com.example.demo.dto.telegram.TelegramResponse;
import com.example.demo.dto.telegram.Update;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



@Service
public class TelegramFeignPollingService {

    private static final Logger log = LoggerFactory.getLogger(TelegramFeignPollingService.class);
    private final OrderService orderService;


    private final TelegramClient telegramClient;
    private long lastUpdateId = 0;

    public TelegramFeignPollingService(OrderService orderService, TelegramClient telegramClient) {
        this.orderService = orderService;
        this.telegramClient = telegramClient;
    }

    @Scheduled(fixedDelay = 1000)
    public void pollMessages() {
            TelegramResponse response = telegramClient.getUpdates(lastUpdateId > 0 ? lastUpdateId + 1 : null, 100);

            if (!response.isOk()) {
                log.error("Failed to fetch updates from Telegram");
                return;
            }

            for (Update update : response.getResult()) {
                lastUpdateId = update.getUpdateId();
                Message message = update.getMessage();
                if (message != null) {
                    String text = message.getText();

                    String telegramId = String.valueOf(message.getFrom() != null ? message.getFrom().getId() : 0);


                    orderService.add(text, telegramId);
                }

            }

    }
}
