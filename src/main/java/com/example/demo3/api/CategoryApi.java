package com.example.demo3.api;

import com.example.demo3.entity.Category;
import com.example.demo3.entity.ResponseObject;
import com.example.demo3.service.CategoryService;
import com.example.demo3.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryApi {
    @Autowired private CategoryService categoryService;
    @Autowired private DishService dishService;
    @GetMapping("/getAll")
    public ResponseEntity<ResponseObject> getApiDish(){
        Object object = categoryService.getAllCategory();
        if(object == null)
            return ResponseEntity.ok(new ResponseObject(201,"Data category fail",null));
        return ResponseEntity.ok(new ResponseObject(200,"Data category success",object));
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseObject> getDishById(@PathVariable Long id){
        Object object = categoryService.findCategoryById(id);
        if(object == null)
            return ResponseEntity.ok(new ResponseObject(201,"Data category fail",null));
        return ResponseEntity.ok(new ResponseObject(200,"Data category success",object));
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> cretateDish(@ModelAttribute Category category){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        if (category == null) {
            return new ResponseEntity<>(new ResponseObject(404,"Data sent fail",null),HttpStatus.BAD_REQUEST);
        } else {
            // Xử lý và trả về response khi request body hợp lệ
            return new ResponseEntity<>(new ResponseObject(200 ,"Updata sucess",categoryService.saveCategory(category)), HttpStatus.OK);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> updateDish(@PathVariable("id") Long id, @ModelAttribute Category category){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);
        return categoryOptional.map(item -> {
            item.setId(category.getId());
            return new ResponseEntity<>(new ResponseObject(200 ,"Updata sucess",categoryService.saveCategory(item)), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new ResponseObject(404,"Data sent fail",null),HttpStatus.OK));
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
