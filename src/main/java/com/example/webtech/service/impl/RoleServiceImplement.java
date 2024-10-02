package com.example.webtech.service.impl;

import com.example.webtech.entity.Role;
import com.example.webtech.repository.RoleRepository;
import com.example.webtech.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImplement implements RoleService {
    @Autowired
    RoleRepository repository;

    @Override
    public void saveOrUpdate(Role role) {
        repository.save(role);
    }

    @Override
    public Set<Role> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"roleId")).stream().collect(Collectors.toSet());
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean checkIfExist(String name) {
        if(repository.findByRoleName(name)==null) {
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Role findByRole(String name) {
        return repository.findByRoleName(name);
    }

    @Override
    public Role findById(long id) {
        return repository.findById(id).get();
    }
}
