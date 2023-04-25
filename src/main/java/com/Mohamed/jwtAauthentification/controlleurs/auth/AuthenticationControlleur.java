package com.Mohamed.jwtAauthentification.controlleurs.auth;

import com.Mohamed.jwtAauthentification.dto.auth.AuthenticationRequest;
import com.Mohamed.jwtAauthentification.dto.auth.AuthenticationResponse;
import com.Mohamed.jwtAauthentification.jwt.JwtUtil;
import com.Mohamed.jwtAauthentification.jwt.UserAuthentificationDetails;
import com.Mohamed.jwtAauthentification.modals.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.Mohamed.jwtAauthentification.utiles.Constants.APP_ROOT;

@RestController
@CrossOrigin
@RequestMapping(APP_ROOT + "/authenticate")
public class AuthenticationControlleur {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> seConnecter(@RequestBody AuthenticationRequest authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
         String jwt = jwtUtil.generateJwtToken(authentication);

        UserAuthentificationDetails authenticetedUser = (UserAuthentificationDetails) authentication.getPrincipal();
        String username = authenticetedUser.getUsername();
        List<String> rolesList = authenticetedUser.getAuthorities()
                .stream().map(item ->item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(new AuthenticationResponse(jwt, username, rolesList));
    }
}
