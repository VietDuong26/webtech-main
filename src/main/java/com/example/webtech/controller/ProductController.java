package com.example.webtech.controller;

import com.example.webtech.dto.ProductDTO;
import com.example.webtech.mapper.ProductMapper;
import com.example.webtech.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@Controller
public class ProductController {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductService productService;
    @Autowired
    SizeService sizeService;
    @Autowired
    ColorService colorService;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CartService cartService;
    @GetMapping("/getAllProduct")
    String getAllProduct(Model model){
        model.addAttribute("products",productService.getAll());
        return "admin/getAllProduct";
    }
    @GetMapping("/addNewProduct")
    String show_addNewProduct(Model model){
        model.addAttribute("new_product",new ProductDTO());
        model.addAttribute("categories",categoryService.getAll());
        return "admin/addNewProduct";
    }
    @PostMapping("/addNewProduct")
    String addNewProduct(Model model, @ModelAttribute("new_product")ProductDTO productDTO) throws IOException {
        productService.saveOrUpdate(productDTO);
        return "redirect:/getAllProduct";
    }
    @GetMapping("/updateProduct/{id}")
    String show_updateProduct(Model model,@PathVariable("id")long id){
        model.addAttribute("selected_product",productMapper.convertToDTO(productService.findById(id)));
        model.addAttribute("categories",categoryService.getAll());
        return "admin/updateProduct";
    }
    @PostMapping("/updateProduct")
    String updateProduct(@ModelAttribute("selected_product")ProductDTO productDTO) throws IOException {
        productService.saveOrUpdate(productDTO);
        return "redirect:/getAllProduct";
    }
    @RequestMapping("/deleteProduct/{id}")
    String deleteProduct(@PathVariable("id")long id){
        productService.delete(id);
        return "redirect:/getAllProduct";
    }
    @GetMapping("/findProductByName")
    String findProductByName(Model model,@RequestParam("name")String name){
        model.addAttribute("products",productService.findByName(name));
        return "admin/getAllProduct";
    }
    @GetMapping("/index")
    String index(Model model){
        model.addAttribute("popular_products",productService.getPopularProducts());
        model.addAttribute("latest_products",productService.getLatestProducts());
        if (userService.checkIfExist(SecurityContextHolder.getContext().getAuthentication().getName())){
            model.addAttribute("user", "existed");
            model.addAttribute("cart_size",cartService.getAllByUserPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName()).size());
        }else{
            model.addAttribute("user","non-existed");
            model.addAttribute("cart_size",0);
        }
        return "index";
    }
    @GetMapping("/detail/{id}")
    String detailProduct(@PathVariable("id")long id,Model model){
        model.addAttribute("product",productService.findById(id));
        if (userService.checkIfExist(SecurityContextHolder.getContext().getAuthentication().getName())){
            model.addAttribute("user", "existed");
            model.addAttribute("cart_size",cartService.getAllByUserPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName()).size());
        }else{
            model.addAttribute("user","non-existed");
            model.addAttribute("cart_size",0);
        }
        return "productDetail";
    }
    @GetMapping("/Man_HomePage")
    String menIndex(Model model){
        model.addAttribute("man_products",productService.findByCategory("Men"));
        if (userService.checkIfExist(SecurityContextHolder.getContext().getAuthentication().getName())){
            model.addAttribute("user", "existed");
            model.addAttribute("cart_size",cartService.getAllByUserPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName()).size());
        }else{
            model.addAttribute("user","non-existed");
            model.addAttribute("cart_size",0);
        }
        return "man_homepage";
    }
    @GetMapping("/Woman_HomePage")
    String womenIndex(Model model){
        model.addAttribute("woman_products",productService.findByCategory("Women"));
        if (userService.checkIfExist(SecurityContextHolder.getContext().getAuthentication().getName())){
            model.addAttribute("user", "existed");
            model.addAttribute("cart_size",cartService.getAllByUserPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName()).size());
        }else{
            model.addAttribute("user","non-existed");
            model.addAttribute("cart_size",0);
        }
        return "woman_homepage";
    }
    @GetMapping("/Kid_HomePage")
    String kidIndex(Model model){
        model.addAttribute("kid_products",productService.findByCategory("Kid"));
        if (userService.checkIfExist(SecurityContextHolder.getContext().getAuthentication().getName())){
            model.addAttribute("user", "existed");
            model.addAttribute("cart_size",cartService.getAllByUserPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName()).size());
        }else{
            model.addAttribute("user","non-existed");
            model.addAttribute("cart_size",0);
        }
        return "kid_homepage";
    }
}
