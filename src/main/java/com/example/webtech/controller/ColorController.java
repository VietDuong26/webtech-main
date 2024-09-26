package com.example.webtech.controller;
import com.example.webtech.entity.Category;
import com.example.webtech.entity.Color;
import com.example.webtech.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ColorController {
    @Autowired
    ColorService colorService;
    @GetMapping("/getAllColor")
    String getAllColor(Model model){
        model.addAttribute("colors",colorService.getAll());
        return "getAllColor";
    }
    @GetMapping("/addNewColor")
    String show_addNewColor(Model model){
        model.addAttribute("new_color",new Color());
        return "addColor";
    }
    @PostMapping("/addNewColor")
    String addNewColor(Model model
            ,@ModelAttribute("new_color")Color new_color
    ){
        if(colorService.checkIfExist(new_color.getColorName())==false){
            colorService.saveOrUpdate(new_color);
        }
        return "redirect:/getAllColor";
    }
    @GetMapping("/updateColor/{id}")
    String show_updateColor(Model model,@PathVariable("id")long id){
        model.addAttribute("selected_color",colorService.findById(id));
        return "updateColor";
    }
    @PostMapping("/updateColor")
    String updateColor(@ModelAttribute("selected_color")Color color){
        colorService.saveOrUpdate(color);
        return "redirect:/getAllColor";
    }
    @RequestMapping("/deleteColor/{id}")
    String deleteCategory(@PathVariable("id")long id){
        colorService.delete(id);
        return "redirect:/getAllColor";
    }
}
