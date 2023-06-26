package com.impt.Gestion_Ecole.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {
    @GetMapping("/")
    public String registrationForm() {
        return "index";
    }

    @RequestMapping("/listuser")
    public String ListUser() {
        return "listUser";
    }




}
