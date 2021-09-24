package com.group8.accountsystem.Controller;

import com.group8.accountsystem.Repository.UserRepository;
import com.group8.accountsystem.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Iterable<User> getAllUsers() {

        return userRepository.findAll();

    }

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false)String name){
//        try {
//            List<User> users = new ArrayList<User>();
//            if (name == null)
//                userRepository.findAll().forEach(users::add);
//            else
//                userRepository.findByNameContaining(name).forEach(users::add);
//            if (users.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user){


        userRepository.save(user);

        return "hello";
    }

}