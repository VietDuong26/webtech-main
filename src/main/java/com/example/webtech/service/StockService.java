package com.example.webtech.service;

import com.example.webtech.dto.ColorDTO;
import com.example.webtech.dto.StockDTO;
import com.example.webtech.dto.StockDTO1;
import com.example.webtech.entity.Color;
import com.example.webtech.entity.Stock;

import java.util.List;

public interface StockService {
    void saveOrUpdate(Stock stock);
    void delete(long id);
    Stock findByProductAndColorAndSize(long product_id,long color_id,long size_id);
    List<Stock> getAll();
    List<Stock> getAllByProductId(int id);

    List<StockDTO> findByProductAndSize(long productId, long sizeId);

    StockDTO findById(long stockId);
    StockDTO1 findByIdEntity(long stockId);
}
