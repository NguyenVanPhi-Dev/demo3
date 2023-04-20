package com.example.demo3.repository;

import com.example.demo3.entity.Dish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DishRepository extends CrudRepository<Dish, Long> {
}
