package com.example.webtech.controller;

import com.example.webtech.entity.Stock;
import com.example.webtech.service.ColorService;
import com.example.webtech.service.ProductService;
import com.example.webtech.service.SizeService;
import com.example.webtech.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StockController {
    @Autowired
    SizeService sizeService;
    @Autowired
    ColorService colorService;
    @Autowired
    ProductService productService;
    @Autowired
    StockService stockService;
    @GetMapping("/receipt")
    String receipt(Model model,@RequestParam(value = "product",required = false)String product_id,
                   @RequestParam(value="color",required = false)String color_id,
                   @RequestParam(value="size",required = false)String size_id,
                   @RequestParam(value="quantity",required = false)String quantity){
        if(product_id!=null&&color_id!=null&&size_id!=null){
            Stock stock=new Stock();
            stock.setQuantity(Integer.parseInt(quantity));
            stock.setSize(sizeService.findById(Integer.parseInt(size_id)));
            stock.setProduct(productService.findById(Integer.parseInt(product_id)));
            stock.setColor(colorService.findById(Integer.parseInt(color_id)));
            stockService.saveOrUpdate(stock);
            return "redirect:/receipt";
        }else {
            model.addAttribute("sizes", sizeService.getAll());
            model.addAttribute("colors", colorService.getAll());
            model.addAttribute("products", productService.getAll());
            return "receipt";
        }
    }
}
