package com.example.webtech.repository;

import com.example.webtech.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Set<Category> findCategoriesByCategoryName(String name);

    Category findByCategoryName(String name);
}
