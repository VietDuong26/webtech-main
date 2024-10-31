package com.example.webtech.service;

import com.example.webtech.dto.OrderDto;
import com.example.webtech.entity.OrderItem;
import com.example.webtech.entity.Orders;

import java.util.List;

public interface OrdersService {
    void checkOut();
    List<Orders> findAllByUserPhoneNumber(String phoneNumber);
    List<OrderDto> getAll();

    void check(String code);
}
