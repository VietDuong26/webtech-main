package com.example.webtech.repository;

import com.example.webtech.entity.Category;
import com.example.webtech.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Set<Product> findByProductName(String name);
    List<Product> findByCategory(Category category);
    @Query(value = "select * from product order by product.sale desc limit 6",nativeQuery = true)
    List<Product> getPopularProducts();
    @Query(value = "select * from product order by product.product_id desc limit 8",nativeQuery = true)
    List<Product> getLatestProducts();
}
