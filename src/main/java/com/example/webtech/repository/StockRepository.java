package com.example.webtech.repository;

import com.example.webtech.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByProduct_ProductIdAndColor_ColorIdAndSize_SizeId(long product_id,long color_id,long size_id);
}
