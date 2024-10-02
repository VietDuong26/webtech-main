package com.example.webtech.security;

import com.example.webtech.entity.Role;
import com.example.webtech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    public CustomUserDetailService(UserRepository repository){
        this.repository=repository;
    }
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        com.example.webtech.entity.User user= repository.findByPhoneNumber(phoneNumber);
        Collection<Role> roles= new HashSet<>();
        roles.add(user.getRole());
        if(user!=null){
            return  new org.springframework.security.core.userdetails.User(user.getPhoneNumber(),
                    user.getPassword(), (Collection<? extends GrantedAuthority>) mapRoleToAuthority(roles));
        }
        return null;
    }
    private Collection<? extends GrantedAuthority> mapRoleToAuthority(Collection<Role> roles){
        Collection<? extends GrantedAuthority> mapRole =roles.stream().map(role-> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return mapRole;
    }
}
