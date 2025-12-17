package com.example.demo.model;

import com.example.demo.enums.FoodStatus;
import com.example.demo.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TBL_FOOD")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    @Column
    private Long price;


}
