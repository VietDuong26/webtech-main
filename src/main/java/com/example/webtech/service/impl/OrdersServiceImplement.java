package com.example.webtech.service.impl;

import com.example.webtech.entity.Orders;
import com.example.webtech.repository.OrdersRepository;
import com.example.webtech.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrdersServiceImplement implements OrdersService {
    @Autowired
    OrdersRepository repository;

    @Override
    public void saveOrUpdate(Orders orders) {

    }

    @Override
    public Set<Orders> getAll() {
        return null;
    }


    @Override
    public void delete(long id) {

    }

    @Override
    public Set<Orders> findByDate(String date) {
        return null;
    }

    @Override
    public Set<Orders> findByUserId(long id) {
        return null;
    }
}
