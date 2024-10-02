package com.example.webtech.service;

import com.example.webtech.entity.Role;

import java.util.Set;

public interface RoleService {
    void saveOrUpdate(Role role);
    Set<Role> getAll();
    void delete(long id);
    boolean checkIfExist(String name);

    Role findByRole(String name);

    Role findById(long id);
}
