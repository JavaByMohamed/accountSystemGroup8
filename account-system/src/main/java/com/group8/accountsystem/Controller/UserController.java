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
import java.util.Optional;

@CrossOrigin("http://localhost:8080")
@RequestMapping(value = "/api", method = RequestMethod.PUT)
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false)String name){
       try {
           List<User> users = new ArrayList<User>();
           if (name == null)
               userRepository.findAll().forEach(users::add);
            else
                userRepository.findByNameContaining(name).forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
           return new ResponseEntity<>(users, HttpStatus.OK);
       }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User>getUserById(@PathVariable("id")String id){
        Optional<User> userData = userRepository.findById(id);

        if(userData.isPresent()){
            return new ResponseEntity<>(userData.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user){


        userRepository.save(user);

        return "hello";
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("id")String id){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //inte klar än
    @PutMapping("/update/{id}")
    public ResponseEntity<User>updateUser(@PathVariable("id")String id, @RequestBody User user){
        Optional<User> userData = userRepository.findById(id);

        if(userData.isPresent()){
            User _user = userData.get();

            _user.setName(user.getName());
            _user.setPassword(user.getPassword());
            _user.setEmail(user.getEmail());
            return new ResponseEntity<>(userRepository.save(_user),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}