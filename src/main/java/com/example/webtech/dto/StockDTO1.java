package com.example.webtech.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockDTO1 {
    private long id;
    private long quantity;
    private long product_id;
    private long color_id;
    private long size_id;
}
