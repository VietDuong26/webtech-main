package com.example.webtech.dto;

import com.example.webtech.entity.Color;
import com.example.webtech.entity.Product;
import com.example.webtech.entity.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemDto {
    private int id;
    private int quantity;
    private String order_code;
    private Product product_id;
    private Color color_id;
    private Size size_id;
}
