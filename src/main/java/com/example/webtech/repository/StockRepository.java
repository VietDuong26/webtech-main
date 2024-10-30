package com.example.webtech.repository;

import com.example.webtech.entity.Color;
import com.example.webtech.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByProduct_ProductIdAndColor_ColorIdAndSize_SizeId(long product_id,long color_id,long size_id);

    List<Stock> findAllByProduct_ProductId(int id);
    @Query(value = "select * from Stock where product_id=? and size_id=?",nativeQuery = true)
    List<Stock> findAllByProduct_ProductIdAndSize_SizeId(long productId, long sizeId);
}
