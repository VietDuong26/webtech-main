package com.example.webtech.service;

import com.example.webtech.entity.OrderItem;
import com.example.webtech.entity.Orders;

import java.util.List;

public interface OrdersService {
    void checkOut();
    List<Orders> findAllByUserPhoneNumber(String phoneNumber);
    List<Orders> getAll();
}
