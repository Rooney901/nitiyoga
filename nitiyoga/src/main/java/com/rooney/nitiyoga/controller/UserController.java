package com.rooney.nitiyoga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rooney.nitiyoga.entity.User;
import com.rooney.nitiyoga.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService userService;

    public ResponseEntity<?> getUserById(Long id) {
        var user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        var createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    public ResponseEntity<?> getUserByUserName(String userName) {
        var user = userService.findUserByUserName(userName);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


}
