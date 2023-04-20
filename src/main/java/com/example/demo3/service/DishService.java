package com.example.demo3.service;

import com.example.demo3.entity.Dish;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DishService {
    List<Dish> getAllDish();

    List<Dish> findByCategoryId(Long categoryId);

    Dish saveDish(Dish dish);

    void deleteDish(Long id);
    void deleteByCategoryId(Long id);

    Optional<Dish> findDishById(Long id);

}
