package com.example.webtech.controller;

import com.example.webtech.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping
    String addToCart(@RequestParam("product_id") long id
            , @RequestParam("quantity") long quantity
            , @RequestParam("size_id") long sid
            , @RequestParam("color_id") long cid) {
        cartService.addToCart(id, quantity);
        return "redirect:/detail/" + id;
    }
}
