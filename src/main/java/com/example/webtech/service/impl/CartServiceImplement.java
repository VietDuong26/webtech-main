package com.example.webtech.service.impl;

import com.example.webtech.entity.CartItem;
import com.example.webtech.repository.CartItemRepository;
import com.example.webtech.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImplement implements CartService {
    @Autowired
    CartItemRepository repository;
    @Override
    public void addToCart(CartItem cartItem) {
        repository.save(cartItem);
    }

    @Override
    public List<CartItem> getAllByUserPhoneNumber(String phoneNumber) {
        return repository.findCartItemByUser_PhoneNumber(phoneNumber);
    }

    @Override
    public CartItem findByUserAndProductAndSizeAndColor(String phoneNumber, long pid, long sid, long cid) {
        return repository.findCartItemByUser_PhoneNumberAndProduct_ProductIdAndSize_SizeIdAndColor_ColorId(phoneNumber,pid,sid,cid);
    }

    @Override
    public void removeFromCart(long id) {
        repository.deleteById(id);
    }

    @Override
    public void minusItem(long id) {
        CartItem cartItem=repository.findById(id).get();
        cartItem.setQuantity(cartItem.getQuantity()-1);
        if (cartItem.getQuantity()>0){
            repository.save(cartItem);
        }else{
            repository.deleteById(id);
        }
    }

    @Override
    public void addOneItem(long id) {
        CartItem cartItem=repository.findById(id).get();
        cartItem.setQuantity(cartItem.getQuantity()+1);
        repository.save(cartItem);
    }
}
