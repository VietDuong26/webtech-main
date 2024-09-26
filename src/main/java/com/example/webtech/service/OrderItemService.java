package com.example.webtech.service;

import com.example.webtech.entity.OrderItem;

import java.util.Set;

public interface OrderItemService {
    void saveOrUpdate(OrderItem orderItem);
    Set<OrderItem> getAll();
    void delete(long id);
    Set<OrderItem> findByDate(String date);
    Set<OrderItem> findByProductId(long id);
    Set<OrderItem> findByOrdersId(long id);
}
