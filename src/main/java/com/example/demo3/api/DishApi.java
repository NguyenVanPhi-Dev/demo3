package com.example.demo3.api;

import com.example.demo3.entity.Dish;
import com.example.demo3.entity.ResponseObject;
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
public class DishApi {
    @Autowired private DishService dishService;
    @GetMapping("/getAllDish")
    public ResponseObject getApiDish(){
        Object dishList = dishService.getAllDish();
        ResponseObject responseObject = new ResponseObject();
        if(dishList == null){
            responseObject.setStatusCode(404);
            responseObject.setContentType("Data not found");
            responseObject.setData(null);
            return responseObject;
        }
        responseObject.setStatusCode(200);
        responseObject.setContentType("Data success");
        responseObject.setData(dishList);
        return responseObject;
    }
    @GetMapping("/getById/{id}")
    public ResponseObject getDishById(@PathVariable Long id){
        Object dish = dishService.findDishById(id);
        if(dish == null)
            return new ResponseObject(404,"Data not found", null);
        else{
            return new ResponseObject(200,"Data find success", dish);
        }
    }

    @PostMapping("/save")
    public ResponseObject cretateDish(@ModelAttribute Dish dish){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");

        if (dish == null) {
            return new ResponseObject(404,"model error",null);
        } else {
            // Xử lý và trả về response khi request body hợp lệ
            return new ResponseObject(200,"Data save success", dishService.saveDish(dish));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseObject UpdateDish(@PathVariable("id") Long id, @ModelAttribute Dish dish){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("charset", "utf-8");
        Optional<Dish> categoryOptional = dishService.findDishById(id);
        return categoryOptional.map(item -> {
            item.setId(dish.getId());
            return new ResponseObject(200,"update success", dishService.saveDish(item));
        }).orElseGet(() -> new ResponseObject(305,"upadte fail",null));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseObject remove(@PathVariable Long id){
        Optional<Dish> dishOptional = dishService.findDishById(id);
       return  dishOptional.map(dish -> {
            dishService.deleteDish(id);
            return new ResponseObject(200,"Delete success",dish);
        }).orElseGet(() -> new ResponseObject(304,"Delete fail",null));

    }
    @GetMapping("dish/findByCategoryId/{id}")
    public ResponseObject searchByCate(@PathVariable("id") Long categoryId){
        List<Dish> dish = dishService.findByCategoryId(categoryId);
        if (dish.isEmpty())
            return new ResponseObject(404,"Find fail", null);
        return new ResponseObject(200,"Find success", dish);
    }
}
