package com.example.demo.service;

import com.example.demo.dto.form.CreateFoodDto;
import com.example.demo.dto.response.FoodDto;
import com.example.demo.model.Food;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    void add(String msg, Long userId);

    @Nullable List<FoodDto> getAll(Long userId, Long foodId, LocalDate date);
}
