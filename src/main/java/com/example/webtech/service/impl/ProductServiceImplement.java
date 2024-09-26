package com.example.webtech.service.impl;

import com.example.webtech.dto.ProductDTO;
import com.example.webtech.entity.Product;
import com.example.webtech.mapper.ProductMapper;
import com.example.webtech.repository.*;
import com.example.webtech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplement implements ProductService {
    @Value("${upload.path}")
    public String folder_path;
    @Autowired
    ProductRepository repository;
    @Autowired
    ProductMapper mapper;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void saveOrUpdate(ProductDTO productDTO) throws IOException {
        MultipartFile file=productDTO.getFile();
        String file_name=file.getOriginalFilename();
        BufferedOutputStream writeFile=new BufferedOutputStream(new FileOutputStream(folder_path + File.separator + file_name));
        writeFile.write(file.getBytes());
        writeFile.close();
        productDTO.setImage(file_name);
        Product product= mapper.convertToEntity(productDTO);
        product.setCategory(categoryRepository.findById(productDTO.getCategory_id()).get());
        repository.save(product);
    }

    @Override
    public Set<Product> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"productId")).stream().collect(Collectors.toSet());
    }


    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Set<Product> findByName(String name) {
        return repository.findByProductName(name);
    }

    @Override
    public Product findById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Product> getPopularProducts() {
        return repository.getPopularProducts();
    }

    @Override
    public List<Product> getLatestProducts() {
        return repository.getLatestProducts();
    }

    @Override
    public List<Product> findByCategory(String name) {
        return repository.findByCategory(categoryRepository.findByCategoryName(name));
    }
}
