package com.example.webtech.service;

import com.example.webtech.dto.ProductDTO;
import com.example.webtech.entity.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProductService {
    void saveOrUpdate(ProductDTO productDTO) throws IOException;
    Set<Product> getAll();
    void delete(long id);
    Set<Product> findByName(String name);
    Product findById(long id);
    List<Product> getPopularProducts();
    List<Product> getLatestProducts();
    List<Product> findByCategory(String name);
}
