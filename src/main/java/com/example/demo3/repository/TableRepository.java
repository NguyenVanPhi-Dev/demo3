package com.example.demo3.repository;

import com.example.demo3.entity.Tables;
import org.springframework.data.repository.CrudRepository;

public interface TableRepository extends CrudRepository<Tables,Long> {
}
