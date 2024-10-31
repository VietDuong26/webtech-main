package com.example.webtech.dto;

import com.example.webtech.entity.OrderItem;
import com.example.webtech.entity.User;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {
    private String code;
    private String createdDate;
    private String status;
    private long total;
    private User user;
    private List<OrderItemDto> orderItems;
}
