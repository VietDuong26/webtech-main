package com.example.webtech.repository;

import com.example.webtech.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SizeRepository extends JpaRepository<Size,Long> {
    Set<Size> findBySizeName(String name);
}
