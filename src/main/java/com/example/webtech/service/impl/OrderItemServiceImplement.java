package com.example.webtech.service.impl;

import com.example.webtech.entity.OrderItem;
import com.example.webtech.repository.OrderItemRepository;
import com.example.webtech.repository.OrdersRepository;
import com.example.webtech.repository.ProductRepository;
import com.example.webtech.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImplement implements OrderItemService {
    @Autowired
    OrderItemRepository repository;

    @Override
    public void saveOrUpdate(OrderItem orderItem) {

    }

    @Override
    public Set<OrderItem> getAll() {
        return null;
    }


    @Override
    public void delete(long id) {

    }

    @Override
    public Set<OrderItem> findByDate(String date) {
        return null;
    }

    @Override
    public Set<OrderItem> findByProductId(long id) {
        return null;
    }

    @Override
    public Set<OrderItem> findByOrdersId(long id) {
        return null;
    }
}
