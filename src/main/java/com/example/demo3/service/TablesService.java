package com.example.demo3.service;

import com.example.demo3.entity.Tables;

import java.util.List;

public interface TablesService {
    List<Tables> getAll();
    Tables saveTable(Tables tables);

}
