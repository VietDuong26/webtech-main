package com.example.webtech.service;

import com.example.webtech.entity.Size;

import java.util.Set;

public interface SizeService {
    void saveOrUpdate(Size size);
    Set<Size> getAll();
    void delete(long id);
    boolean checkIfExist(String name);

    Size findById(long id);
}
