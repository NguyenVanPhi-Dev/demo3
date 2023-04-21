package com.example.demo3.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders_detail")
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private Long orders_id;
    private Long dish_id;
    private String description;

    public OrdersDetail() {
    }

    public OrdersDetail(Long id, Long orders_id, Long dish_id, String description) {
        this.id = id;
        this.orders_id = orders_id;
        this.dish_id = dish_id;
        this.description = description;
    }

    public OrdersDetail(Long orders_id, Long dish_id, String description) {
        this.orders_id = orders_id;
        this.dish_id = dish_id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(Long orders_id) {
        this.orders_id = orders_id;
    }

    public Long getDish_id() {
        return dish_id;
    }

    public void setDish_id(Long dish_id) {
        this.dish_id = dish_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
