package com.example.demo.service;


import com.example.demo.model.Food;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void add(String msg, Long userId, long lastUpdateId,Long chatId);

     List<Food> getAll(Long userId, Long foodId, LocalDate date);

    long getLastMessageId();
}
