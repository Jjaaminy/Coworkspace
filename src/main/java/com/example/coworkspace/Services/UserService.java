package com.example.coworkspace.Services;

import com.example.coworkspace.Model.Role;
import com.example.coworkspace.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {

    private final UserRepo userRepo;

    private RoleRepo roleRepo;

    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    /*
    public class loadUserByUsername(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return "Invalid email or password." ;
        }else{
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user.getRole())
        );
    }

     */
    public void registerUser(User user) {
        if (userRepo.count() == 1) {
            Role admin = roleRepo.findByName(Role.RoleType.ADMIN);
            user.setRole(admin);
        } else {
            Role regularUserRole = roleRepo.findByName(Role.RoleType.REGULAR_USER);
            user.setRole(regularUserRole);
        }
        userRepo.save(user);
    }

    public List<User> getAllRegularUsers() {
        Role adminRole = roleRepo.findByName(Role.RoleType.ADMIN);
        return userRepo.findByRoleNot(adminRole);
    }
}
