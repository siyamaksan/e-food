package com.example.demo.service;

import com.example.demo.dto.response.FoodDto;
import com.example.demo.model.Food;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FoodService {
    List<FoodDto> getAll(String name);

    Food findByCode(String msg);
}
