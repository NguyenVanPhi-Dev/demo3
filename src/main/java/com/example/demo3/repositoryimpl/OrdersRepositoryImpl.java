package com.example.demo3.repositoryimpl;

import com.example.demo3.entity.Order;
import com.example.demo3.repository.OrdersRepository;
import com.example.demo3.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
@Service
public class OrdersRepositoryImpl implements OrdersService {
    @Autowired private OrdersRepository ordersRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Order> getAll() {
        return (List<Order>) ordersRepository.findAll();
    }

    @Override
    public Order saveOrder(Order order) {
        return ordersRepository.save(order);
    }

    @Override
    public Order getByTableId(Long id) {
        String jpql = "SELECT t FROM Orders t where t.table_id = :tableID and status = 1";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("tableID",id);
        return query.getResultList().get(0);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Order getOrderByTableId(Long id) {
        String orderOfTable = "SELECT o FROM Order o where o.table_id = :tableId and status = 1";
        Query query = entityManager.createQuery(orderOfTable,Order.class);
        query.setParameter("tableId",id);
        return (Order) query.getSingleResult();
    }
}
