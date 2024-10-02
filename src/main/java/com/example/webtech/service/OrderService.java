package com.example.webtech.service;

import com.example.webtech.entity.Order;

public interface OrderService {
    void addNew(Order order);
    void delete(long id);
    void getAllByUserPhoneNumber(String phoneNumber);

}
