package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import ru.kata.spring.boot_security.demo.models.User;

@Controller
    public class UserController {


    @GetMapping("/user")
    public String userPage(Authentication authentication, Model model) {

        User user = (User) authentication.getPrincipal();

        model.addAttribute("user", user);

        return "user";
    }
}