package com.example.webtech.controller;

import com.example.webtech.entity.CartItem;
import com.example.webtech.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    SizeService sizeService;
    @Autowired
    ColorService colorService;
    @GetMapping("/Cart")
    String showCart(Model model){
        model.addAttribute("cart_items",cartService.getAllByUserPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName()));
        int subtotal=0;
        for (CartItem item:cartService.getAllByUserPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName())){
            subtotal+=item.getProduct().getPrice()*item.getQuantity();
        }
        if (userService.checkIfExist(SecurityContextHolder.getContext().getAuthentication().getName())){
            model.addAttribute("user", "existed");
        }else{
            model.addAttribute("user","non-existed");
        }
        model.addAttribute("subtotal",subtotal);
        return "cart";
    }
    @RequestMapping("/addToCart")
    String addToCart(@RequestParam("pid")long pid,
                     @RequestParam(value = "sid")long sid,
                     @RequestParam(value = "cid")long cid){
        CartItem item=cartService.findByUserAndProductAndSizeAndColor(SecurityContextHolder.getContext().getAuthentication().getName(),pid,sid,cid);
        if(item!=null){
            item.setQuantity(item.getQuantity()+1);
            cartService.addToCart(item);
        }else{
            CartItem cartItem=new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(productService.findById(pid));
            cartItem.setUser(userService.findByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName()));
            cartItem.setSize(sizeService.findById(sid));
            cartItem.setColor(colorService.findById(cid));
            cartService.addToCart(cartItem);
        }
        return "redirect:/Cart";
    }
    @RequestMapping("/minusItem/{id}")
    String minusItem(@PathVariable("id")long id){
        cartService.minusItem(id);
        return "redirect:/Cart";
    }
    @RequestMapping("/addOneToCart/{id}")
    String addOneItem(@PathVariable("id")long id){
        cartService.addOneItem(id);
        return "redirect:/Cart";
    }
    @RequestMapping("/removeAllFromCart/{id}")
    String removeAll(@PathVariable("id")long id){
        cartService.removeFromCart(id);
        return "redirect:/Cart";
    }
}
