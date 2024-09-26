package com.example.webtech.service.impl;

import com.example.webtech.entity.Size;
import com.example.webtech.repository.SizeRepository;
import com.example.webtech.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SizeServiceImplement implements SizeService {
    @Autowired
    SizeRepository repository;

    @Override
    public void saveOrUpdate(Size size) {
        repository.save(size);
    }

    @Override
    public Set<Size> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"sizeId")).stream().collect(Collectors.toSet());
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean checkIfExist(String name) {
        if(repository.findBySizeName(name).size()==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Size findById(long id) {
        return repository.findById(id).get();
    }
}
