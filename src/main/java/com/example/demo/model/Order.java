package com.example.demo.model;

import com.example.demo.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TBL_ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public LocalDateTime getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(LocalDateTime createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    public LocalDateTime getUpdateOrderTime() {
        return updateOrderTime;
    }

    public void setUpdateOrderTime(LocalDateTime updateOrderTime) {
        this.updateOrderTime = updateOrderTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
