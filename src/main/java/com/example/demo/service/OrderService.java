package com.example.demo.service;


import com.example.demo.model.Food;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void add(String msg, String userId);

     List<Food> getAll(Long userId, Long foodId, LocalDate date);
}
