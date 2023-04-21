package com.example.demo3.service;

import com.example.demo3.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface OrdersService {
    List<Order> getAll();
    Order saveOrder(Order order);
    Order getByTableId(Long id);
    Optional<Order> findById(Long id);
}
