package com.group8.accountsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user){


        userRepository.save(user);

        return "hello";
    }


}