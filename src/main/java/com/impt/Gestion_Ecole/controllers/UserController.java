package com.impt.Gestion_Ecole.controllers;


import com.impt.Gestion_Ecole.model.User;
import com.impt.Gestion_Ecole.services.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/home")
    public String homeOne() {
        return "home";
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("users")
    public User createUserAndProfesseur(@RequestBody User user) {
        // Logique pour créer à la fois l'utilisateur et le professeur
        return userService.createUser(user);
    }






}
