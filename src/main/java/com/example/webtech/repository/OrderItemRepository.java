package com.example.webtech.repository;

import com.example.webtech.entity.OrderItem;
import com.example.webtech.entity.Orders;
import com.example.webtech.entity.Product;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
