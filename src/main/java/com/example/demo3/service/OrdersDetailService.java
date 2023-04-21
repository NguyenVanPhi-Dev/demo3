package com.example.demo3.service;

import com.example.demo3.entity.OrdersDetail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrdersDetailService {
    OrdersDetail saveOrderDetail(OrdersDetail ordersDetail);
    List<OrdersDetail> findByOrdersId(Long id);
}
