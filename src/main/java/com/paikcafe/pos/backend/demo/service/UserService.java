package com.paikcafe.pos.backend.demo.service;

import com.paikcafe.pos.backend.demo.dto.LoginRequest;
import com.paikcafe.pos.backend.demo.dto.LoginResponse;
import com.paikcafe.pos.backend.demo.dto.UserDto;
import com.paikcafe.pos.backend.demo.entity.Branch;
import com.paikcafe.pos.backend.demo.entity.User;
import com.paikcafe.pos.backend.demo.exception.InvalidCredentialsException;
import com.paikcafe.pos.backend.demo.exception.UserNotFoundException;
import com.paikcafe.pos.backend.demo.exception.UsernameAlreadyExistsException;
import com.paikcafe.pos.backend.demo.repository.BranchRepository;
import com.paikcafe.pos.backend.demo.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto) {
        // Check for duplicate username
    	 if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
    	        throw new UsernameAlreadyExistsException("Username already exists");
    	 }
        User user = new User();
        user.setUsername(userDto.getUsername());

        // Hash password
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashedPassword);

        Branch branch = branchRepository.findById(userDto.getBranchId())
            .orElseThrow(() -> new RuntimeException("Branch not found"));
        user.setBranch(branch);

        return userRepository.save(user);
    }
    
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getBranch().getId(),
                user.getBranch().getName()
            );
        } else {
            throw new InvalidCredentialsException();
        }
    }
    
    public LoginResponse getUserDetails(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getBranch().getId(),
                user.getBranch().getName()
        );
    }
    
    public List<LoginResponse> getAllUsers() {
        return userRepository.findAll().stream()
            .map(user -> new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getBranch().getId(),
                user.getBranch().getName()
            ))
            .collect(Collectors.toList());
    }
}
