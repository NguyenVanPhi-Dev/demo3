package com.example.demo3.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long table_id;
    private Long total_price;
    private Long discount;
    private Long final_price;
    private Long status;
    private String description;

    public Order() {
    }

    public Order(Long id, Long table_id, Long total_price, Long discount, Long final_price, Long status, String description) {
        this.id = id;
        this.table_id = table_id;
        this.total_price = total_price;
        this.discount = discount;
        this.final_price = final_price;
        this.status = status;
        this.description = description;
    }

    public Order(Long table_id, Long total_price, Long discount, Long final_price, Long status, String description) {
        this.table_id = table_id;
        this.total_price = total_price;
        this.discount = discount;
        this.final_price = final_price;
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTable_id() {
        return table_id;
    }

    public void setTable_id(Long table_id) {
        this.table_id = table_id;
    }

    public Long getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Long total_price) {
        this.total_price = total_price;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getFinal_price() {
        return final_price;
    }

    public void setFinal_price(Long final_price) {
        this.final_price = final_price;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
