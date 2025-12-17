package com.example.demo.controller;

import com.example.demo.dto.form.CreateFoodDto;
import com.example.demo.dto.response.FoodDto;
import com.example.demo.dto.security.SecurityContext;
import com.example.demo.model.Food;
import com.example.demo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Food>> getAll(@RequestParam(required = false) Long userId,
                                             @RequestParam(required = false) LocalDate date,
                                             @RequestParam(required = false) Long foodId
    ) {
        return ResponseEntity
                .ok()
                .header("X-Source", "UserService")
                .body(orderService.getAll(userId,foodId,date));
    }
}
