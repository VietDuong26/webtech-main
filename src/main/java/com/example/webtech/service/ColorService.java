package com.example.webtech.service;

import com.example.webtech.entity.Color;

import java.util.Set;

public interface ColorService {
    void saveOrUpdate(Color color);
    Set<Color> getAll();
    void delete(long id);
    boolean checkIfExist(String name);

    Color findById(long id);
}
