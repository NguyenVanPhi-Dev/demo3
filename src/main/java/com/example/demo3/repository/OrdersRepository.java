package com.example.demo3.repository;

import com.example.demo3.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order,Long> {
}
