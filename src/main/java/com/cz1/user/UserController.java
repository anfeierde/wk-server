package com.cz1.user;

import com.cz1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wkchen on 2017/3/21.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<User> getUsers() {
        return repository.findAll();
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return repository.findByUsername(username);
    }
}