package com.example.webtech.controller;

import com.example.webtech.entity.Role;
import com.example.webtech.entity.Size;
import com.example.webtech.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;
    @GetMapping("/getAllRole")
    String getAllRole(Model model){
        model.addAttribute("roles",roleService.getAll());
        return "getAllRole";
    }
    @GetMapping("/addNewRole")
    String show_addNewSize(Model model){
        model.addAttribute("new_role",new Role());
        return "addRole";
    }
    @PostMapping("/addNewRole")
    String addNewColor(Model model
            ,@ModelAttribute("new_role")Role new_role
    ){
        if(roleService.checkIfExist(new_role.getRoleName())==false){
            roleService.saveOrUpdate(new_role);
        }
        return "redirect:/getAllRole";
    }
    @GetMapping("/updateRole/{id}")
    String show_updateRole(Model model,@PathVariable("id")long id){
        model.addAttribute("selected_role",roleService.findById(id));
        return "updateRole";
    }
    @PostMapping("/updateRole")
    String updateRole(@ModelAttribute("selected_role")Role role){
        roleService.saveOrUpdate(role);
        return "redirect:/getAllRole";
    }
    @RequestMapping("/deleteRole/{id}")
    String deleteSize(@PathVariable("id")long id){
        roleService.delete(id);
        return "redirect:/getAllRole";
    }
}
