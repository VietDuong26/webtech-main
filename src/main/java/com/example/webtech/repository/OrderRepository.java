package com.example.webtech.repository;

import com.example.webtech.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,String> {
    List<Orders> findAllByUserPhoneNumber(String phoneNumber);
    List<Orders> findAllByCode(String code);
}
