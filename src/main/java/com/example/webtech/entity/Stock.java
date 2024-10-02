package com.example.webtech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="size_id")
    private Size size;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="color_id")
    private Color color;
}
