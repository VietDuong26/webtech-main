package com.example.webtech.mapper;

import com.example.webtech.dto.OrderDto;
import com.example.webtech.entity.Orders;
import com.example.webtech.repository.OrderItemRepository;
import com.example.webtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Autowired
    UserService userService;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderItemMapper orderItemMapper;
    public OrderDto toDto(Orders orders){
        return OrderDto.builder()
                .code(orders.getCode())
                .createdDate(orders.getCreatedDate())
                .status(orders.getStatus())
                .total(orders.getTotal())
                .user(orders.getUser())
                .orderItems(orderItemRepository.findOrderItemByOrder_code(orders.getCode()).stream().map(x->orderItemMapper.toDtoOrderItemDto(x)).collect(Collectors.toList()))
                .build();
    }
}
