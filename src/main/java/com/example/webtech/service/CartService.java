package com.example.webtech.service;

import com.example.webtech.entity.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getAllByUserPhone(String phoneNumber);
    void addToCart(long productId,long quantity);
    void deleteFromCart(long id);
    CartItem findByProductId(long id);
}
