package com.example.demo.service.telegram;

import com.example.demo.dto.form.SendMessageRequest;
import com.example.demo.dto.telegram.Message;
import com.example.demo.dto.telegram.TelegramResponse;
import com.example.demo.dto.telegram.Update;
import com.example.demo.service.FoodService;
import com.example.demo.service.OrderService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;


@Service
public class TelegramFeignPollingService {

    private static final Logger log = LoggerFactory.getLogger(TelegramFeignPollingService.class);
    private final OrderService orderService;
    private final FoodService foodService;


    private final TelegramClient telegramClient;
    long lastUpdateId = 0;

    public TelegramFeignPollingService(OrderService orderService, FoodService foodService, TelegramClient telegramClient) {
        this.orderService = orderService;
        this.foodService = foodService;
        this.telegramClient = telegramClient;
    }

    @PostConstruct
    public void init() {
        lastUpdateId = orderService.getLastMessageId();

    }

    @Scheduled(fixedDelay = 3000)
    public void pollMessages() {

        TelegramResponse response = telegramClient.getUpdates(lastUpdateId > 0 ? lastUpdateId + 1 : null, 100);

        if (!response.isOk()) {
            log.error("Failed to fetch updates from Telegram");
            return;
        }


        response.getResult().sort(Comparator.comparing(Update::getUpdateId));

        for (Update update : response.getResult()) {
            lastUpdateId = update.getUpdateId();
            Message message = update.getMessage();
            if (message != null) {
                log.error("received message : {}", update);

                String text = message.getText();

                Long chatId = message.getChat().getId();

                if (text.equals("/start"))
                    sendFoodList(chatId);

                Long telegramId = message.getFrom() != null ? message.getFrom().getId() : 0;


                orderService.add(text, telegramId, lastUpdateId, message.getChat().getId());
                sendMessage(chatId,"order Received!!");
            }

        }

    }

    private void sendFoodList(Long chatId){
        String text = foodService.findAll().stream()
                .map(food -> food.getCode() +" " + food.getName())
                .distinct()
                .collect(Collectors.joining("\n"));
        sendMessage(chatId,text);
    }

    private void sendMessage(Long chatId,String txt) {


        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .chatId(chatId)
                .text(txt).parseMode("Markdown").build();
        telegramClient.sendMessage(sendMessageRequest);

    }
}
