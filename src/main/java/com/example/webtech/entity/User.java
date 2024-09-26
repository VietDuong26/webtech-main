package com.example.webtech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userName;
    private String address;
    private String phoneNumber;
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Orders> orders;
    @ManyToOne
    @JoinColumn(name="role_id",nullable = false)
    private Role role;
}
