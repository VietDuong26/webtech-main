package com.example.webtech.mapper;

import com.example.webtech.entity.OrderItem;
import com.example.webtech.repository.ColorRepository;
import com.example.webtech.repository.ProductRepository;
import com.example.webtech.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    ColorRepository colorRepository;
    public com.example.webtech.dto.OrderItemDto toDtoOrderItemDto(OrderItem item){
        return com.example.webtech.dto.OrderItemDto.builder()
                .id(item.getId())
                .order_code(item.getOrder_code())
                .product_id(productRepository.findById((long) item.getId()).get())
                .size_id(sizeRepository.findById((long) item.getSize_id()).get())
                .color_id(colorRepository.findById((long) item.getColor_id()).get())
                .quantity(item.getQuantity())
                .build();
    }
}
