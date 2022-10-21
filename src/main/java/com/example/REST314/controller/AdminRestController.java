package com.example.REST314.controller;

import com.example.REST314.model.User;
import com.example.REST314.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class AdminRestController {

    private final UserService userservice;


    @Autowired
    public AdminRestController(UserService userservice) {
        this.userservice = userservice;
    }


    @GetMapping("/api/admin")
    public List<User> getAllUsers() {
        return userservice.getAllUsers();
    }


    @PostMapping("api/admin")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        userservice.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PutMapping("api/admin/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        userservice.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("api/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userservice.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
