package com.example.webtech.service;


import com.example.webtech.entity.User;

import java.util.Set;

public interface UserService {
    void saveOrUpdate(User user);
    Set<User> getAll();
    void delete(long id);
    Set<User> findByName(String name);
    Set<User> findByRole(long id);
    boolean checkIfExist(String phoneNumber);

    User findById(long id);
}
