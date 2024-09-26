package com.example.webtech.repository;

import com.example.webtech.entity.Role;
import com.example.webtech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Set<User> findByUserName(String name);
    Set<User> findByRole(Role role);
    User findByPhoneNumber(String phoneNumber);
}
