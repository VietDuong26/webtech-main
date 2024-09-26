package com.example.webtech.controller;

import com.example.webtech.entity.*;
import com.example.webtech.service.RoleService;
import com.example.webtech.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @GetMapping("/getAllUser")
    String getAllUser(Model model){
        model.addAttribute("users",userService.getAll().stream().filter(x->!x.getRole().getRoleName().equals("ROLE_ADMIN")));
        return "getAllUser";
    }
    @GetMapping("/addNewUser")
    String show_addNewUser(Model model){
        model.addAttribute("new_user",new User());
        return "addNewUser";
    }
    @PostMapping("/addNewUser")
    String addNewProduct(@ModelAttribute("new_user")User user){
        if(userService.checkIfExist(user.getPhoneNumber())==false){
            userService.saveOrUpdate(user);
        }
        return "redirect:/getAllUser";
    }
    @GetMapping("/updateUser/{id}")
    String show_updateUser(Model model, @PathVariable("id")long id){
        model.addAttribute("selected_user",userService.findById(id));
        return "updateUser";
    }
    @PostMapping("/updateUser")
    String updateUser(@ModelAttribute("selected_user")User user){
        userService.saveOrUpdate(user);
        return "redirect:/getAllUser";
    }
    @PostMapping("/deleteUser/{id}")
    String deleteUser(@PathVariable("id")long id){
        userService.delete(id);
        return "redirect:/getAllUser";
    }
    @GetMapping("/findUserByName")
    String findUserByName(Model model,@RequestParam("name")String name){
        model.addAttribute("users",userService.findByName(name));
        return "getAllUser";
    }
    @GetMapping("/findUserByRole")
    String findUserByRole(Model model,@RequestParam("id")long id){
        model.addAttribute("users",userService.findByRole(id));
        return "getAllUser";
    }
    @GetMapping("/login")
    String login(Model model){
        model.addAttribute("user",new User());
        return "login";
    }
    @RequestMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
    @GetMapping("/regist")
    String signup(Model model){
        model.addAttribute("user",new User());
        return "signup";
    }
}
