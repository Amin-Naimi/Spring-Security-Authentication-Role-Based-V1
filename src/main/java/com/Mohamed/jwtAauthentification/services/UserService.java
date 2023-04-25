package com.Mohamed.jwtAauthentification.services;

import com.Mohamed.jwtAauthentification.modals.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

     public List<Users> findAllUsers();

     public ResponseEntity<Users> findByUserId(Long userId);

     public ResponseEntity<Users> createUser(Users user);

     public ResponseEntity<Users> updateUser(Users userData, Long userId);

     public ResponseEntity<String> deleteUserById(Long userId);
}
