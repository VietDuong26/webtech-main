package com.example.webtech.controller;

import com.example.webtech.entity.CartItem;
import com.example.webtech.service.CartService;
import com.example.webtech.service.OrdersService;
import com.example.webtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    OrdersService ordersService ;
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
        long total=0;
        for (CartItem x: cartService.getAllByUserPhone(SecurityContextHolder.getContext().getAuthentication().getName())){
            total+=x.getQuantity()*x.getProduct().getPrice();
        }
        model.addAttribute("total",total);
        return "cart";
    }
    @RequestMapping("/addToCart")
    String addToCart(@RequestParam(value = "product_id",required = false) String pid
            , @RequestParam(value = "quantity", required = false) String quantity
            , @RequestParam(value = "size_id",required = false) String sid
            , @RequestParam(value = "color_id",required = false) String cid
            , @RequestParam(value = "cart_id", required = false) String cartId) {
        cartService.addToCart(cartId, quantity, pid, sid, cid);
        if (cartId == null) {
            return "redirect:/detail/" + pid;
        }else{
            return "redirect:/cart";
        }
    }
    @RequestMapping("/removeFromCart")
    String removeFromCart(@RequestParam("cart_id")String cartId){
        cartService.deleteFromCart(Long.valueOf(cartId));
        return "redirect:/cart";
    }
    @RequestMapping("/deleteOneFromCart")
    String deleteOneFromCart(@RequestParam("cart_id")String cartId){
        cartService.deleteOneFromCart(Long.valueOf(cartId));
        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    ResponseEntity<Boolean> checkout(){
        ordersService.checkOut();
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
