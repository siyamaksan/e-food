package com.example.demo.dto.response;

import lombok.Getter;
import lombok.Setter;


public interface FoodDto {

    Long getId();
    String getName();
    String getCode();
    Long getPrice();

}
