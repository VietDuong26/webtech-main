package com.example.webtech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="orders_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private String order_code;
    private int product_id;
    private int color_id;
    private int size_id;
}
