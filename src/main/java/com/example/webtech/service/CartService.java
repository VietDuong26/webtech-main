package com.example.webtech.service;

import com.example.webtech.entity.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getAllByUserPhone(String phoneNumber);
    void addToCart(String cartItemId,String quantity,String productId,String colorId,String sizeId);
    void deleteFromCart(long id);
    CartItem findCartItem(long uid,long pid,long cid,long sid);
    void deleteOneFromCart(long id);
}
