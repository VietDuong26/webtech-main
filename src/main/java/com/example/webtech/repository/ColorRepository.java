package com.example.webtech.repository;

import com.example.webtech.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ColorRepository extends JpaRepository<Color,Long> {
    List<Color> findByColorName(String name);
}
