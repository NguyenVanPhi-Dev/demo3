package com.example.demo3.entity;

import javax.persistence.*;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer count;
    private String unit;
    private Double price;
    private Integer status;
    private String image;
    private Long category_id;

    public Dish() {
    }

    public Dish(Long id, String name, Integer count, String unit, Double price, Integer status, String image, Long category_id) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.unit = unit;
        this.price = price;
        this.status = status;
        this.image = image;
        this.category_id = category_id;
    }

    public Dish(String name, Integer count, String unit, Double price, Integer status, String image, Long category_id) {
        this.name = name;
        this.count = count;
        this.unit = unit;
        this.price = price;
        this.status = status;
        this.image = image;
        this.category_id = category_id;
    }


    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", category_id=" + category_id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }


}
