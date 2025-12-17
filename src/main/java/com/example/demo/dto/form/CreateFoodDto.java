package com.example.demo.dto.form;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFoodDto {

    private List<FoodAmountDto> list;

    public List<FoodAmountDto> getList() {
        return list;
    }

    public void setList(List<FoodAmountDto> list) {
        this.list = list;
    }
}
