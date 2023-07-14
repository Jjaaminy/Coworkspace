package com.example.coworkspace.Controller;


import com.example.coworkspace.Model.Role;
import com.example.coworkspace.Model.User;
import com.example.coworkspace.Services.UserRepo;
import com.example.coworkspace.Services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserRepo userRepo;

    public UserController(UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }


    @GetMapping("/regular")
    public ResponseEntity<List<User>> getAllRegularUsers() {
        List<User> regularUsers = userService.getAllRegularUsers();
        return ResponseEntity.ok(regularUsers);
    }

}


