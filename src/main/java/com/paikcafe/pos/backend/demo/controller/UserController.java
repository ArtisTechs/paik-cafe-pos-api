package com.paikcafe.pos.backend.demo.controller;

import com.paikcafe.pos.backend.demo.dto.LoginRequest;
import com.paikcafe.pos.backend.demo.dto.LoginResponse;
import com.paikcafe.pos.backend.demo.dto.UserDto;
import com.paikcafe.pos.backend.demo.entity.User;
import com.paikcafe.pos.backend.demo.service.UserService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST /api/users
    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
    
    // GET /api/users/{id}
    @GetMapping("/{id}")
    public LoginResponse getUserDetails(@PathVariable UUID id) {
        return userService.getUserDetails(id);
    }
    
 // GET /api/users
    @GetMapping
    public List<LoginResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
