package com.example.webtech.repository;

import com.example.webtech.entity.OrderItem;
import com.example.webtech.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Query(value = "select * from orders_item where order_code=:code",nativeQuery = true)
    List<OrderItem> findOrderItemByOrder_code(@Param("code") String code);
}
