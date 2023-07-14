package com.example.coworkspace.Controller;

import com.example.coworkspace.Model.User;
import com.example.coworkspace.Services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    //registrieren
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

//mitgliederliste
    @GetMapping("/regular")
    public ResponseEntity<List<User>> getAllRegularUsers() {
        List<User> regularUsers = userService.getAllRegularUsers();
        return ResponseEntity.ok(regularUsers);
    }

    //admin
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAdmin() {
        List<User> regularUsers = userService.getAllRegularUsers();
        return ResponseEntity.ok(regularUsers);
    }

    //adminrolle zuteilen
    @PostConstruct
    public void init() {
        userService.assignAdmin();
    }
}


