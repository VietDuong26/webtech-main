package com.example.webtech.controller;

import com.example.webtech.entity.Category;
import com.example.webtech.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/getAllCategory")
    String getAllCategory(Model model){
        model.addAttribute("categories",categoryService.getAll());
        return "getAllCategory";
    }
    @GetMapping("/addNewCategory")
    String show_addNewCategory(Model model){
        model.addAttribute("new_category",new Category());
        return "addCategory";
    }
    @PostMapping("/addNewCategory")
    String addNewCategory(Model model
            ,@ModelAttribute("new_category")Category new_category
    ){
            if(categoryService.checkIfExist(new_category.getCategoryName())==false){
                categoryService.saveOrUpdate(new_category);
            }
        return "redirect:/getAllCategory";
    }
    @GetMapping("/updateCategory/{id}")
    String show_updateCategory(Model model,@PathVariable("id")long id){
        model.addAttribute("selected_category",categoryService.findById(id));
        return "updateCategory";
    }
    @PostMapping("/updateCategory")
    String updateCategory(@ModelAttribute("selected_category")Category category){
        categoryService.saveOrUpdate(category);
        return "redirect:/getAllCategory";
    }
    @RequestMapping("/deleteCategory/{id}")
    String deleteCategory(@PathVariable("id")long id){
        categoryService.delete(id);
        return "redirect:/getAllCategory";
    }
    @GetMapping("/findCategoryByName")
    String findCategoryByName(Model model,@RequestParam("name")String name){
        model.addAttribute("categories",categoryService.findByName(name));
        return "getAllCategory";
    }
}
