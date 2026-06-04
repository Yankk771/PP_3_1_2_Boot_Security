package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());

        return "admin";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/admin";
    }
    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {

        User user = userService.getUserById(id);

        model.addAttribute("user", user);

        return "edit";
    }
    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/admin";
    }

}