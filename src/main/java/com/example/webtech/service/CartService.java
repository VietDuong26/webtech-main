package com.example.webtech.service;

import com.example.webtech.entity.CartItem;

import java.util.List;

public interface CartService {
    void addToCart(CartItem cartItem);
    List<CartItem> getAllByUserPhoneNumber(String phoneNumber);
    CartItem findByUserAndProductAndSizeAndColor(String phoneNumber,long pid,long sid,long cid);
    void removeFromCart(long id);

    void minusItem(long id);

    void addOneItem(long id);
}
