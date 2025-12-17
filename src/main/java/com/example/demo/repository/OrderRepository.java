package com.example.demo.repository;

import com.example.demo.dto.response.FoodDto;
import com.example.demo.model.Food;
import com.example.demo.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT f FROM Order o JOIN o.food f " +
            "WHERE o.createOrderTime = :date " +  // <-- fix the property name here
            "AND (:userId IS NULL OR o.user.id = :userId) " +
            "AND (:foodId IS NULL OR f.id = :foodId)")
    List<Food> findFoodByOptionalFilters(
            @Param("userId") Long userId,
            @Param("foodId") Long foodId,
            @Param("date") LocalDate date);

    @Query("SELECT COALESCE(MAX(o.messageId), 0) FROM Order o")
    long findMaxMessageId();
}