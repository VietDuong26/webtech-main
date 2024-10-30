package com.example.webtech.mapper;

import com.example.webtech.dto.StockDTO;
import com.example.webtech.dto.StockDTO1;
import com.example.webtech.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public StockDTO toDto(Stock stock){
        return StockDTO.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .product(stock.getProduct().getProductName())
                .color(stock.getColor().getColorName())
                .size(stock.getSize().getSizeName())
                .build();
    }
    public StockDTO1 toDto1(Stock stock){
        return StockDTO1.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .product_id(stock.getProduct().getProductId())
                .color_id(stock.getColor().getColorId())
                .size_id(stock.getSize().getSizeId())
                .build();
    }
}
