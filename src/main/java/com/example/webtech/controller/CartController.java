package com.example.webtech.controller;

import com.example.webtech.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @GetMapping("/cart")
    String showCart(Model model){
        return "cart";
    }
    @RequestMapping("/addToCart")
    String addToCart(@RequestParam("product_id") String pid
            , @RequestParam(value = "quantity", required = false) String quantity
            , @RequestParam("size_id") String sid
            , @RequestParam("color_id") String cid
            , @RequestParam(value = "cart_id", required = false) String cartId) {
        cartService.addToCart(cartId, quantity, pid, sid, cid);
        if (cartId == null) {
            return "redirect:/detail/" + pid;
        }else{
            return "redirect:/cart";
        }
    }
}
