package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.models.Role;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());

        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("roleId") Long roleId) {

        Role role = roleRepository.findById(roleId).orElse(null);
        user.setRoles(Set.of(role));

        userService.saveUser(user);

        return "redirect:/admin";
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {

        User user = userService.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());

        return "edit";
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("roleId") Long roleId) {

        Role role = roleRepository.findById(roleId).orElse(null);
        user.setRoles(Set.of(role));

        userService.saveUser(user);

        return "redirect:/admin";
    }

}