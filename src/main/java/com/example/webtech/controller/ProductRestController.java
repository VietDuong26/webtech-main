package com.example.webtech.controller;

import com.example.webtech.dto.ColorDTO;
import com.example.webtech.dto.StockDTO;
import com.example.webtech.dto.StockDTO1;
import com.example.webtech.entity.Color;
import com.example.webtech.entity.Stock;
import com.example.webtech.service.OrdersService;
import com.example.webtech.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    StockService stockService;
    @Autowired
    OrdersService ordersService;
    @GetMapping("/getColorByProductAndSize")
    List<StockDTO> getColorByProductAndSize(@RequestParam("product_id")long productId,
                                            @RequestParam("size_id")long sizeId){
        return stockService.findByProductAndSize(productId,sizeId);
    }
    @GetMapping("/getColorByProductAndSizeAndColor")
    StockDTO getColorByProductAndSizeAndColor(@RequestParam("stock_id")long stockId){
        StockDTO stockDTO=stockService.findById(stockId);
        return stockDTO;
    }
    @GetMapping("/getInfor")
    StockDTO1 getInfor(@RequestParam("stock_id")long stockId){
        return stockService.findByIdEntity(stockId);
    }

}
