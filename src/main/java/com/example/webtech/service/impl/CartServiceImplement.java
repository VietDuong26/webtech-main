package com.example.webtech.service.impl;

import com.example.webtech.entity.CartItem;
import com.example.webtech.entity.User;
import com.example.webtech.repository.*;
import com.example.webtech.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImplement implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<CartItem> getAllByUserPhone(String phoneNumber) {
        return cartRepository.findCartItemByUser_PhoneNumber(phoneNumber);
    }

    @Override
    public void addToCart(String cartItemId, String quantity, String productId, String colorId, String sizeId) {
        User user=userRepository.findByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        if (cartItemId != null) {//Thêm khi đang ở trang giỏ hàng
            CartItem cartItem = cartRepository.findById(Long.valueOf(cartItemId)).get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartRepository.save(cartItem);
        } else {//Thêm khi ngoài giỏ hàng
            CartItem cartItem = cartRepository.findCartItemByUser_UserIdAndProduct_ProductIdAndColor_ColorIdAndSize_SizeId(user.getUserId(),Long.valueOf(productId),Long.valueOf(colorId),Long.valueOf(sizeId));
            if (cartItem != null) {
                if (quantity == null) {//Kiểm tra nếu có số lượng hay không? Nếu không thì mặc định thêm 1
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    cartRepository.save(cartItem);
                }else{
                    cartItem.setQuantity(cartItem.getQuantity() + Long.valueOf(quantity));
                    cartRepository.save(cartItem);
                }
            } else {
                CartItem item=new CartItem();
                if(quantity==null){
                    item.setQuantity(1);
                }else{
                    item.setQuantity(Long.parseLong(quantity));
                }
                item.setUser(user);
                item.setProduct(productRepository.findById(Long.valueOf(productId)).get());
                item.setColor(colorRepository.findById(Long.valueOf(colorId)).get());
                item.setSize(sizeRepository.findById(Long.valueOf(sizeId)).get());
                cartRepository.save(item);
            }
        }
    }

    @Override
    public void deleteFromCart(long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartItem findCartItem(long uid,long pid,long cid,long sid) {
        return cartRepository.findCartItemByUser_UserIdAndProduct_ProductIdAndColor_ColorIdAndSize_SizeId(uid,pid,cid,sid);
    }

    @Override
    public void deleteOneFromCart(long id) {
        CartItem cartItem = cartRepository.findById(id).get();
        if(cartItem.getQuantity()>1){
            cartItem.setQuantity(cartItem.getQuantity()-1);
            cartRepository.save(cartItem);
        }else{
            cartRepository.deleteById(id);
        }
    }
}
