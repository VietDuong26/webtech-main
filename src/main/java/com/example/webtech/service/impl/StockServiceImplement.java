package com.example.webtech.service.impl;

import com.example.webtech.dto.ColorDTO;
import com.example.webtech.dto.StockDTO;
import com.example.webtech.dto.StockDTO1;
import com.example.webtech.entity.Color;
import com.example.webtech.entity.Stock;
import com.example.webtech.mapper.ColorMapper;
import com.example.webtech.mapper.StockMapper;
import com.example.webtech.repository.ColorRepository;
import com.example.webtech.repository.StockRepository;
import com.example.webtech.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImplement implements StockService {
    @Autowired
    ColorMapper mapper;
    @Autowired
    StockRepository repository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    StockMapper stockMapper;
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

    @Override
    public List<Stock> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Stock> getAllByProductId(int id) {
        return repository.findAllByProduct_ProductId(id);
    }

    @Override
    public List<StockDTO> findByProductAndSize(long productId, long sizeId) {
        List<Stock> list=repository.findAllByProduct_ProductIdAndSize_SizeId(productId,sizeId);
        return list.stream().map(x->stockMapper.toDto(x)).collect(Collectors.toList());
    }

    @Override
    public StockDTO findById(long stockId) {
        return stockMapper.toDto(repository.findById(stockId).get());
    }

    @Override
    public StockDTO1 findByIdEntity(long stockId) {
        return stockMapper.toDto1(repository.findById(stockId).get());
    }
}
