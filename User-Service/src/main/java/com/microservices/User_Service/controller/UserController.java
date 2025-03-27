package com.microservices.User_Service.controller;

import com.microservices.User_Service.model.User;
import com.microservices.User_Service.repository.UserRepository;
import com.microservices.User_Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
