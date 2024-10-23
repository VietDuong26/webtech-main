package com.example.webtech.mapper;

import com.example.webtech.dto.ProductDTO;
import com.example.webtech.entity.Product;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.util.Locale;

@Component
public class ProductMapper {
    public String formatPrice(long price){
        Locale locale=new Locale("vn","VN");
        return NumberFormat.getCurrencyInstance(locale).format(price);
    }
    public ProductDTO convertToDTO(Product product){
        return ProductDTO.builder()
                .productId(product.getProductId())
                .image(product.getImage())
                .price(formatPrice(product.getPrice()))
                .description(product.getDescription())
                .productName(product.getProductName())
                .build();
    }
    public Product convertToEntity(ProductDTO productDTO){
        return Product.builder()
                .productId(productDTO.getProductId())
                .image(productDTO.getImage())
                .price(Long.parseLong(productDTO.getPrice()))
                .description(productDTO.getDescription())
                .productName(productDTO.getProductName())
                .build();
    }
}
