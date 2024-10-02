package com.example.webtech.service;

import com.example.webtech.entity.Stock;

public interface StockService {
    void saveOrUpdate(Stock stock);
    void delete(long id);
    Stock findByProductAndColorAndSize(long product_id,long color_id,long size_id);
}
