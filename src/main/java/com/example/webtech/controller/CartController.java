package com.example.webtech.controller;

import com.example.webtech.service.CartService;
import com.example.webtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @GetMapping("/cart")
    String showCart(Model model){
        if (userService.checkIfExist(SecurityContextHolder.getContext().getAuthentication().getName())){
            model.addAttribute("user", "existed");
            model.addAttribute("cart_size",cartService.getAllByUserPhone(SecurityContextHolder.getContext().getAuthentication().getName()).size());
        }else{
            model.addAttribute("user","non-existed");
            model.addAttribute("cart_size",0);
        }
        model.addAttribute("cart_items",cartService.getAllByUserPhone(SecurityContextHolder.getContext().getAuthentication().getName()));
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
