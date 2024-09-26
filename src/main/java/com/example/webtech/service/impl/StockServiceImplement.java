package com.example.webtech.service.impl;

import com.example.webtech.entity.Stock;
import com.example.webtech.repository.StockRepository;
import com.example.webtech.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImplement implements StockService {
    @Autowired
    StockRepository repository;
    @Override
    public void saveOrUpdate(Stock stock) {
        repository.save(stock);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Stock findByProductAndColorAndSize(long product_id, long color_id, long size_id) {
        return repository.findByProduct_ProductIdAndColor_ColorIdAndSize_SizeId(product_id, color_id, size_id);
    }
}
