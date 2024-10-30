package com.example.webtech.dto;

import com.example.webtech.entity.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockDTO {
    private long id;
    private long quantity;
    private String product;
    private String color;
    private String size;
}
