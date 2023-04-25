package com.Mohamed.jwtAauthentification.controlleurs;

import com.Mohamed.jwtAauthentification.controlleurs.api.UserApi;
import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.Mohamed.jwtAauthentification.utiles.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT +"/api")
public class UserControlleur implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public List<Users> findAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public ResponseEntity<Users> findByUserId(Long userId) {
        return userService.findByUserId(userId);
    }

    @Override
    public ResponseEntity<Users> createUser(Users user) {
        return userService.createUser(user);
    }

    @Override
    public ResponseEntity<Users> updateUser(Users user, Long userId) {
        return userService.updateUser(user, userId);
    }

    @Override
    public ResponseEntity<String> deleteUserById(Long userId) {
        return userService.deleteUserById(userId);
    }
}
