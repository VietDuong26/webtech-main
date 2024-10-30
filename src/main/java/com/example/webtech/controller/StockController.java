package com.example.webtech.controller;

import com.example.webtech.entity.Stock;
import com.example.webtech.mapper.ProductMapper;
import com.example.webtech.service.ColorService;
import com.example.webtech.service.ProductService;
import com.example.webtech.service.SizeService;
import com.example.webtech.service.StockService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockController {
    Gson gson = new Gson();
    @Autowired
    SizeService sizeService;
    @Autowired
    ColorService colorService;
    @Autowired
    ProductService productService;
    @Autowired
    StockService stockService;
    @Autowired
    ProductMapper mapper;

    @GetMapping("/receipt")
    String show_receipt(Model model) {
        model.addAttribute("sizes", sizeService.getAll());
        model.addAttribute("colors", colorService.getAll());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("new_stock", new Stock());
        return "admin/receipt";
    }

    @PostMapping("/receipt")
    String receipt(@RequestParam(value = "product") String product_id,
                   @RequestParam(value = "color", required = false) String color_id,
                   @RequestParam(value = "size", required = false) String size_id,
                   @RequestParam(value = "quantity", required = false) String quantity) {
        if (stockService.findByProductAndColorAndSize(Long.parseLong(product_id), Long.parseLong(color_id), Long.parseLong(size_id)) == null) {
            Stock stock = new Stock();
            stock.setQuantity(Integer.parseInt(quantity));
            stock.setSize(sizeService.findById(Integer.parseInt(size_id)));
            stock.setProduct(mapper.convertToEntity(productService.findById(Integer.parseInt(product_id))));
            stock.setColor(colorService.findById(Integer.parseInt(color_id)));
            stockService.saveOrUpdate(stock);
        } else {
            Stock stock = stockService.findByProductAndColorAndSize(Long.parseLong(product_id), Long.parseLong(color_id), Long.parseLong(size_id));
            stock.setQuantity(stock.getQuantity() + Long.parseLong(quantity));
            stockService.saveOrUpdate(stock);
        }

        return "redirect:/receipt";
    }

    @GetMapping("/stock")
    String getStock(Model model) {
        model.addAttribute("stocks", stockService.getAll());
        return "admin/admin-stock-list";
    }

    @GetMapping("/getQuantity")
    String getQuantity(Model model,
                       @RequestParam("product_id") int product_id,
                       @RequestParam("color_id") int color_id,
                       @RequestParam("size_id") int size_id) {
        model.addAttribute("quantity", stockService.findByProductAndColorAndSize(product_id, color_id, size_id).getQuantity());
        return "admin/receipt";
    }
}
