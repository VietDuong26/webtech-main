package com.example.webtech.service.impl;

import com.example.webtech.entity.Color;
import com.example.webtech.repository.ColorRepository;
import com.example.webtech.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ColorServiceImplement implements ColorService {
    @Autowired
    ColorRepository repository;

    @Override
    public void saveOrUpdate(Color color) {
        repository.save(color);
    }

    @Override
    public Set<Color> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"colorId")).stream().collect(Collectors.toSet());
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean checkIfExist(String name) {
        if(repository.findByColorName(name).size()==0) {
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Color findById(long id) {
        return repository.findById(id).get();
    }
}
