package com.example.webtech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long colorId;
    private String colorName;
    @OneToMany(mappedBy = "color")
    private List<Stock> stocks;
}
