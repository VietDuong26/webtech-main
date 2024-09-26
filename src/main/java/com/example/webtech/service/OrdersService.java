package com.example.webtech.service;

import com.example.webtech.entity.Orders;
import java.util.Set;

public interface OrdersService {
    void saveOrUpdate(Orders orders);
    Set<Orders> getAll();
    void delete(long id);
    Set<Orders> findByDate(String date);
    Set<Orders> findByUserId(long id);
}
