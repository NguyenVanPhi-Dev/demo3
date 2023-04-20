package com.example.demo3.api;

import com.example.demo3.entity.Category;
import com.example.demo3.entity.Dish;
import com.example.demo3.service.CategoryService;
import com.example.demo3.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryApi {
    @Autowired private CategoryService categoryService;
    @Autowired private DishService dishService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getApiDish(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Category> getDishById(@PathVariable Long id){
        Category category = categoryService.findCategoryById(id).get();
        if(category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Category>(category,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Category> cretateDish(@ModelAttribute Category category){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            // Xử lý và trả về response khi request body hợp lệ
            return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> UpdateDish(@PathVariable("id") Long id, @ModelAttribute Category category){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        return categoryOptional.map(item -> {
            item.setId(category.getId());
            return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> remove(@PathVariable("id") Long id){
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        return  categoryOptional.map(category -> {
            dishService.deleteByCategoryId(id);
            categoryService.deleteCategory(id);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
