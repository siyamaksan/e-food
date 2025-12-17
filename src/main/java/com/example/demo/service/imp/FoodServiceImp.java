package com.example.demo.service.imp;

import com.example.demo.dto.response.FoodDto;
import com.example.demo.model.Food;
import com.example.demo.repository.FoodRepository;
import com.example.demo.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FoodServiceImp implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImp(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<FoodDto> getAll(String name) {
        return foodRepository.findAllByName(name);
    }

    @Override
    public Food findByCode(String msg) {
        return foodRepository.findByCode(msg)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found"));

    }
}
