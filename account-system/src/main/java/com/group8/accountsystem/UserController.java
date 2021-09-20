package com.group8.accountsystem;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/users")
    public List<String> getAllUsers() {
        return null;
    }


}