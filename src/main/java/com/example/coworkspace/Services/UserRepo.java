package com.example.coworkspace.Services;

import com.example.coworkspace.Model.Role;
import com.example.coworkspace.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);

    List<User> findByRoleNot(Role role);

}
