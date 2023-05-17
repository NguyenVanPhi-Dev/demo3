package com.example.demo3.api;

import com.example.demo3.entity.*;
import com.example.demo3.service.DishService;
import com.example.demo3.service.OrdersDetailService;
import com.example.demo3.service.OrdersService;
import com.example.demo3.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bill")
public class BillApi {
    @Autowired
    @Lazy
    private OrdersService ordersService;
    @Autowired
    @Lazy
    private OrdersDetailService ordersDetailService;
    @Autowired
    @Lazy
    private TablesService tablesService;
    @Autowired
    @Lazy
    private DishService dishService;
    @GetMapping("getBill")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = ordersService.getAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping("getDetailByOrder/{id}")
    public ResponseEntity<List<OrdersDetail>> getDetail(@PathVariable("id") Long id){
        List<OrdersDetail> ordersDetails = ordersDetailService.findByOrdersId(id);
        if (ordersDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ordersDetails, HttpStatus.OK);
    }
    @GetMapping("getTable")
    public ResponseEntity<List<Tables>> getTable(){
        List<Tables> tablesList = tablesService.getAll();
        if(tablesList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tablesList,HttpStatus.OK);
    }
    @PostMapping("addOrderDetail")
    public ResponseEntity<OrdersDetail> saveOrderDetail(@ModelAttribute OrdersDetail ordersDetail){
        OrdersDetail newDetail = ordersDetailService.saveOrderDetail(ordersDetail);
        if(newDetail == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newDetail,HttpStatus.OK);
    }
    @GetMapping("getBillByTableId/{id}")
    public ResponseEntity<ResponseObject> getBillByTableId(@PathVariable("id") Long id){
        Map<String,Object> dataReturn = new HashMap<>();

        Order order = ordersService.getOrderByTableId(id);
        ResponseObject responseObject = new ResponseObject();
        if(order == null){
            responseObject.setStatusCode(403);
            responseObject.setContentType("Bill not exists");
            responseObject.setData(null);
            return new ResponseEntity<>(responseObject,HttpStatus.OK);
        }else
            dataReturn.put("Bill",order);

        List<Dish> ordersDetailList = dishService.getByOrderId(order.getId());
        if(ordersDetailList.isEmpty()){
            dataReturn.put("dishList",null);
        }else {
            dataReturn.put("dishList", ordersDetailList);
        }
        return ResponseEntity.ok(new ResponseObject(200,"Find bill success",dataReturn));
    }
    @GetMapping("getAllTable")
    public ResponseEntity<ResponseObject> listTable(){
        Object tableList = tablesService.getAll();
        ResponseObject responseObject = new ResponseObject();
        if(tableList == null){
            responseObject.setStatusCode(403);
            responseObject.setContentType("Table not found");
            responseObject.setData(null);
            return ResponseEntity.ok(responseObject);
        }
        else{
            responseObject.setStatusCode(200);
            responseObject.setContentType("Data list table");
            responseObject.setData(tableList);
            return ResponseEntity.ok(responseObject);
        }
    }
        @GetMapping("test123/{id}")
    public   ResponseEntity<ResponseObject> testss(@PathVariable("id") Long id){
        Object obj = dishService.testjoin(id);
        return ResponseEntity.ok(new ResponseObject(200,"find join 2 table", obj));
    }

}
