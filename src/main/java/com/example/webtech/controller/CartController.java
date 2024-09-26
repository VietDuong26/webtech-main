package com.example.webtech.controller;

import com.example.webtech.dto.CartItem;
import com.example.webtech.entity.Product;
import com.example.webtech.service.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @RequestMapping("/addToCart")
    String addToCart(@RequestParam("id")long id, HttpServletResponse response, HttpServletRequest request){
        return "redirect:/index";
    }
    @GetMapping("/Cart")
    String show_cart(@CookieValue(name="cartList")String cartList){
        return "cart";
    }
}
