package com.example.coworkspace.Services;

import com.example.coworkspace.Model.Role;
import com.example.coworkspace.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public void assignAdmin() {
        List<User> regularUsers = userRepo.findAll();
        if (!regularUsers.isEmpty()) {
            User firstUser = regularUsers.get(0);
            Role adminRole = roleRepo.findByName(Role.RoleType.ADMIN); // Assuming you have a role repository to retrieve the "ADMIN" role
            firstUser.setRole(adminRole);
            userRepo.save(firstUser);
        }
    }

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
