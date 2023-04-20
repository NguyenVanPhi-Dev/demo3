package com.example.demo3.service;

import com.example.demo3.entity.Category;
import com.example.demo3.entity.Dish;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategory();

    Category saveCategory(Category category);

    void deleteCategory(Long id);

    Optional<Category> findCategoryById(Long id);

}
