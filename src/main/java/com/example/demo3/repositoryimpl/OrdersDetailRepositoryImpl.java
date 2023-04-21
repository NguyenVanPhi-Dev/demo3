package com.example.demo3.repositoryimpl;

import com.example.demo3.entity.OrdersDetail;
import com.example.demo3.repository.OrdersDetailRepository;
import com.example.demo3.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Service
public class OrdersDetailRepositoryImpl implements OrdersDetailService {
    @Autowired private OrdersDetailRepository ordersDetailRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public OrdersDetail saveOrderDetail(OrdersDetail ordersDetail) {
        return ordersDetailRepository.save(ordersDetail);
    }

    @Override
    public List<OrdersDetail> findByOrdersId(Long id) {
        String sql = "SELECT O FROM OrdersDetail O where O.orders_id = :orderId ";
        TypedQuery<OrdersDetail> query = entityManager.createQuery(sql, OrdersDetail.class);
        query.setParameter("orderId",id);
        return query.getResultList();
    }
}
