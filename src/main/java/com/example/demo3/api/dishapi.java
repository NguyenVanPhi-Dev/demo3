package com.example.demo3.api;

import com.example.demo3.entity.Dish;
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
@RequestMapping("api")
public class dishapi {
    @Autowired private DishService dishService;
    @GetMapping("/getAllDish")
    public ResponseEntity<List<Dish>> getApiDish(){
        return ResponseEntity.ok(dishService.getAllDish());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id){
        Dish dish = dishService.findDishById(id).get();
        if(dish == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
        return new ResponseEntity<Dish>(dish,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Dish> cretateDish(@ModelAttribute Dish dish){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        if (dish == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            // Xử lý và trả về response khi request body hợp lệ
            return new ResponseEntity<>(dishService.saveDish(dish), HttpStatus.OK);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Dish> UpdateDish(@PathVariable("id") Long id, @ModelAttribute Dish dish){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        Optional<Dish> categoryOptional = dishService.findDishById(id);
        return categoryOptional.map(item -> {
            item.setId(dish.getId());
            return new ResponseEntity<>(dishService.saveDish(dish), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Dish> remove(@PathVariable Long id){
        Optional<Dish> dishOptional = dishService.findDishById(id);
       return  dishOptional.map(dish -> {
            dishService.deleteDish(id);
            return new ResponseEntity<Dish>(dish, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @GetMapping("dish/findByCategoryId/{id}")
    public ResponseEntity<List<Dish>> searchByCate(@PathVariable("id") Long CategoryId){
        return ResponseEntity.ok(dishService.findByCategoryId(CategoryId));
    }
}
