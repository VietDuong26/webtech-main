package com.example.webtech.repository;

import com.example.webtech.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findCartItemByUser_PhoneNumber(String phoneNumber);

    CartItem findCartItemByProduct_ProductId(long id);

    CartItem findCartItemByUser_UserIdAndProduct_ProductIdAndColor_ColorIdAndSize_SizeId(long userId,long productId,long colorId,long sizeId);
}
