package com.example.demo3.repository;

import com.example.demo3.entity.Dish;
import com.example.demo3.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
                        public class DishRepositoryImpl implements DishService {
    @Autowired private DishRepository dishRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Dish> getAllDish() {
        return (List<Dish>)dishRepository.findAll();
    }

    @Override
    public List<Dish> findByCategoryId(Long categoryId) {
        String jpql = "SELECT d FROM Dish d WHERE d.category_id = :categoryId";
        TypedQuery<Dish> query = entityManager.createQuery(jpql, Dish.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    @Override
    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void deleteByCategoryId(Long categoryId) {
        String jpql = "DELETE FROM Dish d WHERE d.category_id = :categoryId";
        System.out.println("running delete dish by category id "+ categoryId);
        Query query = entityManager.createQuery(jpql);
        query.setParameter("categoryId", categoryId);
        System.out.println("Delete countDish: "+ query.executeUpdate());

//        query.executeUpdate();
    }


    @Override
    public Optional<Dish> findDishById(Long id) {
        return dishRepository.findById(id);
    }
}
