package com.example.demo.repository;

import com.example.demo.dto.response.FoodDto;
import com.example.demo.model.Food;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<FoodDto> findAllByName(String name);

    Optional<Food> findByCode(String msg);
}
