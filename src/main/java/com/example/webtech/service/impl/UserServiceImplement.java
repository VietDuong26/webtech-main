package com.example.webtech.service.impl;

import com.example.webtech.entity.Role;
import com.example.webtech.entity.User;
import com.example.webtech.repository.RoleRepository;
import com.example.webtech.repository.UserRepository;
import com.example.webtech.service.RoleService;
import com.example.webtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImplement implements UserService {
    private PasswordEncoder passwordEncode;
    @Autowired
    UserRepository repository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleService roleService;
    public UserServiceImplement(UserRepository repository,RoleRepository roleRepository
            , PasswordEncoder passwordEncode
    ){
        this.passwordEncode=passwordEncode;
        this.repository=repository;
        this.roleRepository=roleRepository;
    }
    @Override
    public void saveOrUpdate(User user) {
        user.setPassword(passwordEncode.encode(user.getPassword()));
        user.setRole(roleRepository.findByRoleName("ROLE_USER"));
        repository.save(user);
    }

    @Override
    public Set<User> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"userId")).stream().collect(Collectors.toSet());
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Set<User> findByName(String name) {
        return repository.findByUserName(name).stream().collect(Collectors.toSet());
    }

    @Override
    public Set<User> findByRole(long id) {
        return repository.findByRole(roleRepository.findById(id).get()).stream().collect(Collectors.toSet());
    }

    @Override
    public boolean checkIfExist(String phoneNumber) {
        if(repository.findByPhoneNumber(phoneNumber)==null){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public User findById(long id) {
        return repository.findById(id).get();
    }
}
