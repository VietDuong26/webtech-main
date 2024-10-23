package com.example.webtech.service.impl;

import com.example.webtech.entity.Category;
import com.example.webtech.repository.CategoryRepository;
import com.example.webtech.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplement implements CategoryService {
    @Autowired
    CategoryRepository repository;

    @Override
    public void saveOrUpdate(Category category) {
        repository.save(category);
    }
    @Override
    public Set<Category> getAll() {
        return repository.findAllByOrderByCategoryIdAsc();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Set<Category> findByName(String name) {
        return (Set<Category>) repository.findCategoriesByCategoryNameOrderByCategoryIdAsc(name);
    }

    @Override
    public boolean checkIfExist(String name) {
        if(repository.findCategoriesByCategoryNameOrderByCategoryIdAsc(name).size()==0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Category findById(long id) {
        return repository.findById(id).get();
    }
}
