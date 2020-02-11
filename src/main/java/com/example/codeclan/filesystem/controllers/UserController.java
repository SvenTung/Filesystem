package com.example.codeclan.filesystem.controllers;

import com.example.codeclan.filesystem.models.User;
import com.example.codeclan.filesystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users")
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    @DeleteMapping(value = "/users/{id}")
    public Optional<User> deleteUserById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        userRepository.deleteById(id);
        return user;
    }

    @PatchMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User payload, @PathVariable Long id){
        if (payload.getId() == null){
            payload.setId(id);
        } else if(payload.getId() != id){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(payload);
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
