package com.example.webtech.service.impl;

import com.example.webtech.entity.Order;
import com.example.webtech.repository.OrderRepository;
import com.example.webtech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    OrderRepository repository;
    @Override
    public void addNew(Order order) {
        repository.save(order);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void getAllByUserPhoneNumber(String phoneNumber) {
        repository.findOrderByUser_PhoneNumber(phoneNumber);
    }
}
