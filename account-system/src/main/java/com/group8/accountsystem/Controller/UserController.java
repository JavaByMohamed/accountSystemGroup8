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
@RequestMapping(value = "/api"/*, method = RequestMethod.PUT*/) // Detta är varför PUT gav Ambiguous method error
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Hämtar alla users eller alla med ett visst namn - kan presentera resultatet i XML och JSON
    @GetMapping(path = "/users",
            consumes ={org.springframework.http.MediaType.APPLICATION_XML_VALUE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE},
            produces = {org.springframework.http.MediaType.APPLICATION_XML_VALUE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
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

    // Hämtar en user med ett visst id, om den finns
    @GetMapping("/users/{id}")
    public ResponseEntity<User>getUserById(@PathVariable("id")String id){
        Optional<User> userData = userRepository.findById(id);

        if(userData.isPresent()){
            return new ResponseEntity<>(userData.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Postar en ny user och visar resultatet i XML eller JSON
    @PostMapping(path = "/users",
            consumes ={org.springframework.http.MediaType.APPLICATION_XML_VALUE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE},
            produces = {org.springframework.http.MediaType.APPLICATION_XML_VALUE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Uppdaterar en user (annan endpoint)
    @PutMapping("/users/{id}")
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

    // Tar bort en user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("id")String id){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Rensar databasen
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}