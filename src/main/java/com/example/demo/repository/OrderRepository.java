package com.example.demo.repository;

import com.example.demo.dto.response.FoodDto;
import com.example.demo.model.Order;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("""
        SELECT f AS foodDto
        FROM Order o
        JOIN o.food f
        WHERE o.date = :date
          AND (:userId IS NULL OR o.user.id = :userId)
          AND (:foodId IS NULL OR o.food.id = :foodId)
        """)
    List<FoodDto> findFoodByOptionalFilters(
            @Param("userId") Long userId,
            @Param("foodId") Long foodId,
            @Param("date") LocalDate date);
}
