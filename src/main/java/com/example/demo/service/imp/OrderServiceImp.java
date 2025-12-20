package com.example.demo.service.imp;

import com.example.demo.model.Food;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.FoodService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final FoodService foodService;

    public OrderServiceImp(OrderRepository orderRepository, UserService userService, FoodService foodService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.foodService = foodService;
    }

    @Transactional
    @Override
    public void add(String msg, Long telegramId, long lastUpdateId,Long chatId) {


        User user = userService.findByTelegramId(telegramId);

        Food food = foodService.findByCode(msg);

        Order order = Order.builder()
                .user(user)
                .food(food)
                .chatId(chatId)
                .messageId(lastUpdateId).build();

        orderRepository.save(order);

    }

    @Override
    public  List<Food> getAll(Long userId, Long foodId, LocalDate date) {
        return orderRepository.findFoodByOptionalFilters(userId,foodId,date);
    }

    @Override
    public long getLastMessageId() {
        return orderRepository.findMaxMessageId();

    }


}
