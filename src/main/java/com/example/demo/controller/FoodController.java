package com.example.demo.controller;

import com.example.demo.dto.response.FoodDto;
import com.example.demo.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<List<FoodDto>> getAll(@RequestParam(required = false) String name
    ) {
        return ResponseEntity
                .ok()
                .header("X-Source", "UserService")
                .body(foodService.getAll(name));
    }
}
