package com.example.demo3.api;

import com.example.demo3.entity.Dish;
import com.example.demo3.entity.ResponseObject;
import com.example.demo3.service.DishService;
import org.apache.catalina.connector.Response;
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
    public ResponseEntity<ResponseObject> getApiDish(){
        Object dishList = dishService.getAllDish();
        ResponseObject responseObject = new ResponseObject();
        if(dishList == null){
            responseObject.setStatusCode(404);
            responseObject.setContentType("Data not found");
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject,HttpStatus.OK);
        }
        responseObject.setStatusCode(200);
        responseObject.setContentType("Data success");
        responseObject.setData(dishList);
        return new ResponseEntity<>(responseObject,HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseObject> getDishById(@PathVariable Long id){
        Object dish = dishService.findDishById(id);
        if(dish == null)
            return new ResponseEntity<>(new ResponseObject(404,"Data not found", null),HttpStatus.OK);
        else
        return new ResponseEntity<>(new ResponseObject(200,"Data find success", dish),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObject> cretateDish(@ModelAttribute Dish dish){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        if (dish == null) {
            return new ResponseEntity<>(new ResponseObject(404,"model error",null),HttpStatus.BAD_REQUEST);
        } else {
            // Xử lý và trả về response khi request body hợp lệ
            return new ResponseEntity<>(new ResponseObject(200,"Data save success", dishService.saveDish(dish)), HttpStatus.OK);
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
