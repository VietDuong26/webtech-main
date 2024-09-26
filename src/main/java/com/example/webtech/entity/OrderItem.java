package com.example.webtech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderItemId;
    private String date;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name="order_id",nullable = false)
    private Orders order;
}
