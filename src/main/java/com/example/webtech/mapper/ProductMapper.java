package com.example.webtech.mapper;

import com.example.webtech.dto.ProductDTO;
import com.example.webtech.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO convertToDTO(Product product){
        return ProductDTO.builder()
                .productId(product.getProductId())
                .image(product.getImage())
                .price(product.getPrice())
                .description(product.getDescription())
                .productName(product.getProductName())
                .build();
    }
    public Product convertToEntity(ProductDTO productDTO){
        return Product.builder()
                .productId(productDTO.getProductId())
                .image(productDTO.getImage())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .productName(productDTO.getProductName())
                .build();
    }
}
