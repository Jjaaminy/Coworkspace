package com.example.coworkspace.Controller;

import com.example.coworkspace.Model.Role;
import com.example.coworkspace.Model.User;
import com.example.coworkspace.Services.UserRepo;
import com.example.coworkspace.Services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepo repo;
    public UserController(UserService userService,UserRepo repo) {
        this.userService = userService;
        this.repo = repo;

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

    //mitgliederlisteverwalten
    //Admin kann Buchung akzeptieren oder ablehnen
    @PostMapping()
    public ResponseEntity<User> Users(@RequestBody User user ) {
        Role.RoleType userRole = user.getRole().getName();
        if (userRole == Role.RoleType.ADMIN) {
            if (user.getState() != null && user.getState()) {
                //true=behalten
                user.setState(true);
            } else {
                //false zum löschen
                user.setState(false);
            }
            User saveuser = repo.save(user);
            repo.saveAndFlush(saveuser);
            return ResponseEntity.ok(saveuser);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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


