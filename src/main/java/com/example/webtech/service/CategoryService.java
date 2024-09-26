package com.example.webtech.service;

import com.example.webtech.entity.Category;

import java.util.Set;

public interface CategoryService {
    void saveOrUpdate(Category category);
    Set<Category> getAll();
    void delete(long id);
    Set<Category> findByName(String name);
    boolean checkIfExist(String name);

    Category findById(long id);
}
