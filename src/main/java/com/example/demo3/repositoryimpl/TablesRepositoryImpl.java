package com.example.demo3.repositoryimpl;

import com.example.demo3.entity.Tables;
import com.example.demo3.repository.TableRepository;
import com.example.demo3.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TablesRepositoryImpl implements TablesService {

    @Autowired
    private TableRepository tableRepository;
    @Override
    public List<Tables> getAll() {
        return (List<Tables>) tableRepository.findAll();
    }

    @Override
    public Tables saveTable(Tables tables) {
        return tableRepository.save(tables);
    }
}
