package com.example.demo3.repositoryimpl;

import com.example.demo3.entity.Order;
import com.example.demo3.repository.OrdersRepository;
import com.example.demo3.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
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
}
