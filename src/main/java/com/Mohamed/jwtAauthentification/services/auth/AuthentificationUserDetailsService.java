package com.Mohamed.jwtAauthentification.services.auth;

import com.Mohamed.jwtAauthentification.exception.ResourceNotFoundException;
import com.Mohamed.jwtAauthentification.jwt.UserAuthentificationDetails;
import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Could not able to find the user.");
        return new UserAuthentificationDetails(user);
    }

    }

