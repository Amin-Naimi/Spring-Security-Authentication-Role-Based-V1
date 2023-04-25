package com.Mohamed.jwtAauthentification.services.impl;

import com.Mohamed.jwtAauthentification.exception.ResourceNotFoundException;
import com.Mohamed.jwtAauthentification.modals.Roles;
import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.repositorys.RolesRepository;
import com.Mohamed.jwtAauthentification.repositorys.UserRepository;
import com.Mohamed.jwtAauthentification.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Users> findByUserId(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with Id" + userId.toString()+ "not found in DB"));
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<Users> createUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<Users> updateUser(Users userData, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with Id" + userId.toString()+ "not found in DB"));
        user.setUsername(userData.getUsername());
        user.setPassword(user.getPassword());
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<String> deleteUserById(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with Id" + userId.toString()+ "not found in DB"));
        userRepository.deleteById(user.getId());
        return ResponseEntity.ok().body("User id: "+userId+" is deleted.");
    }
}
