package com.example.demo3.api;

import com.example.demo3.entity.Order;
import com.example.demo3.entity.OrdersDetail;
import com.example.demo3.entity.Tables;
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

import java.util.List;

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
    private ResponseEntity<OrdersDetail> saveOrderDetail(@ModelAttribute OrdersDetail ordersDetail){
        OrdersDetail newDetail = ordersDetailService.saveOrderDetail(ordersDetail);
        if(newDetail == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newDetail,HttpStatus.OK);
    }
}
