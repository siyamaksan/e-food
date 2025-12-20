package com.example.demo.model;

import com.example.demo.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TBL_ORDER")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Column
    private Long messageId;

    @Column
    private Long chatId;

    @Column
    private LocalDateTime createOrderTime;

    @Column
    private LocalDateTime updateOrderTime;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @PrePersist
    protected void onCreate() {
        this.createOrderTime = LocalDateTime.now();
    }

    // optionally, you can add @PreUpdate for updateDate
    @PreUpdate
    protected void onUpdate() {
        this.updateOrderTime = LocalDateTime.now();
    }



}
