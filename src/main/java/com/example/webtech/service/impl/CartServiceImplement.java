package com.example.webtech.service.impl;

import com.example.webtech.entity.CartItem;
import com.example.webtech.repository.CartRepository;
import com.example.webtech.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartServiceImplement implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Override
    public List<CartItem> getAllByUserPhone(String phoneNumber) {
        return cartRepository.findCartItemByUser_PhoneNumber(phoneNumber);
    }

    @Override
    public void addToCart(long productId, long quantity) {
        CartItem cartItem=cartRepository.findCartItemByProduct_ProductId(productId);
        if(cartItem!=null){
            if (quantity!=0){
                cartItem.setQuantity(cartItem.getQuantity()+quantity);
            }else{
                cartItem.setQuantity(cartItem.getQuantity()+1);
            }
            cartRepository.save(cartItem);
        }else{
            cartRepository.save(cartItem);
        }
    }

    @Override
    public void deleteFromCart(long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartItem findByProductId(long id) {
        return cartRepository.findCartItemByProduct_ProductId(id);
    }
}
