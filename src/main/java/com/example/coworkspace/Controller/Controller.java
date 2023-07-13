package com.example.coworkspace.Controller;

import com.example.coworkspace.Model.Buchung;
import com.example.coworkspace.Model.User;
import com.example.coworkspace.Services.BuchungRepo;
import com.example.coworkspace.Services.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {
    private UserRepo repo;
    ;
    private ArrayList<User> list;


    @Autowired
    public Controller(UserRepo repo) {
        this.repo = repo;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Logik zur Validierung und Speicherung des Benutzers in der Datenbank
        User savedUser = repo.save(user);
        return ResponseEntity.ok(savedUser);
    }
}


