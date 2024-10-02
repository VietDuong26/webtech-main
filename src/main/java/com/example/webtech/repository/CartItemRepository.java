package com.example.webtech.repository;

import com.example.webtech.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
     CartItem findCartItemByUser_PhoneNumberAndProduct_ProductIdAndSize_SizeIdAndColor_ColorId(String phoneNumber,long pid,long sid,long cid);
     List<CartItem> findCartItemByUser_PhoneNumber(String phoneNumber);

}
