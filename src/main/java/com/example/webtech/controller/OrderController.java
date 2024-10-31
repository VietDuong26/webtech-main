package com.example.webtech.controller;

import com.example.webtech.entity.Orders;
import com.example.webtech.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @Autowired
    OrdersService ordersService;
    @GetMapping("/getAllOrder")
    String getAllOrder(Model model){
        model.addAttribute("orders",ordersService.getAll());
        return "admin/admin-order-list";
    }
    @GetMapping("/checkOrder")
    String checkOrder(@RequestParam("code")String code){
        ordersService.check(code);
        return "redirect:/getAllOrder";
    }
}
