package com.Mohamed.jwtAauthentification.controlleurs.api;

import com.Mohamed.jwtAauthentification.modals.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserApi {

    @GetMapping("/users/all") //ADMIN and EDITOR
    @PreAuthorize("hasAnyAuthority('aez','EDITOR')")
    List<Users> findAllUsers();

    @PostMapping("/users/create")
    ResponseEntity<Users> createUser(@RequestBody Users user);

    // C'est Le meme pour les autres méthodes.
    @GetMapping("/users/{userId}")
    //@PreAuthorize("hasAuthority('EDITOR')")
    ResponseEntity<Users> findByUserId(@PathVariable("userId")Long userId);

    @PutMapping("/users/{userId}")
    ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable("userId") Long userId);

    @DeleteMapping("/users/{userId}")
    ResponseEntity<String> deleteUserById(@PathVariable Long userId);


}
