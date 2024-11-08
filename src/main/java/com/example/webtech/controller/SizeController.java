package com.example.webtech.controller;

import com.example.webtech.entity.Size;
import com.example.webtech.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SizeController {
    @Autowired
    SizeService sizeService;

    @GetMapping("/getAllSize")
    String getAllSize(Model model) {
        model.addAttribute("sizes", sizeService.getAll());
        return "admin/admin-size_list";
    }

    @GetMapping("/addNewSize")
    String show_addNewSize(Model model) {
        model.addAttribute("new_size", new Size());
        return "admin/admin-size_add";
    }

    @PostMapping("/addNewSize")
    String addNewColor(Model model
            , @ModelAttribute("new_size") Size new_size
    ) {
        if (sizeService.checkIfExist(new_size.getSizeName()) == false && new_size.getSizeName() != "") {
            sizeService.saveOrUpdate(new_size);
        }
        return "redirect:/getAllSize";
    }

    @GetMapping("/updateSize/{id}")
    String show_updateSize(Model model, @PathVariable("id") long id) {
        model.addAttribute("selected_size", sizeService.findById(id));
        return "admin/admin-size_update";
    }

    @PostMapping("/updateSize")
    String updateSize(@ModelAttribute("selected_size") Size size) {
        if (!size.getSizeName().equals("") && !sizeService.checkIfExist(size.getSizeName())) {
            sizeService.saveOrUpdate(size);
        }
        return "redirect:/getAllSize";
    }

    @RequestMapping("/deleteSize/{id}")
    String deleteSize(@PathVariable("id") long id) {
        sizeService.delete(id);
        return "redirect:/getAllSize";
    }
}
