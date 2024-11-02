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

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/getAllUser")
    String getAllUser(Model model) {
        model.addAttribute("users", userService.getAll().stream().filter(x -> !x.getRole().getRoleName().equals("ROLE_ADMIN")));
        return "admin/admin-user-list";
    }

    @GetMapping("/addNewUser")
    String show_addNewUser(Model model) {
        model.addAttribute("new_user", new User());
        return "admin/admin-user-add";
    }

    @PostMapping("/addNewUser")
    String addNewProduct(@ModelAttribute("new_user") User user) {
        if (userService.checkIfExist(user.getPhoneNumber()) == false) {
            userService.saveOrUpdate(user);
        }
        return "redirect:/getAllUser";
    }

    @GetMapping("/updateUser/{id}")
    String show_updateUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("selected_user", userService.findById(id));
        return "admin/admin-user-update";
    }

    @PostMapping("/updateUser")
    String updateUser(@ModelAttribute("selected_user") User user) {
        userService.saveOrUpdate(user);
        return "redirect:/getAllUser";
    }

    @RequestMapping("/deleteUser/{id}")
    String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/getAllUser";
    }

    @GetMapping("/findUserByName")
    String findUserByName(Model model, @RequestParam("name") String name) {
        model.addAttribute("users", userService.findByName(name).stream().filter(user -> !user.getRole().getRoleName().equals("ROLE_ADMIN")));
        return "admin/admin-user-list";
    }

    @GetMapping("/findUserByRole")
    String findUserByRole(Model model, @RequestParam("id") long id) {
        model.addAttribute("users", userService.findByRole(id));
        return "admin/admin-user-list";
    }

    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("user", new User());
        if (userService.checkIfExist(SecurityContextHolder.getContext().getAuthentication().getName())) {
            model.addAttribute("user", "existed");
        } else {
            model.addAttribute("user", "non-existed");
        }
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping("/regist")
    String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/regist")
    String signUp(@ModelAttribute("user") User user) {
        if (!userService.checkIfExist(user.getPhoneNumber())) {
            userService.saveOrUpdate(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/admin_index")
    String getAll() {
        return "admin/index_admin";
    }
}
