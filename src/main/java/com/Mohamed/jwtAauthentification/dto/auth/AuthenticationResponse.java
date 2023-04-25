package com.Mohamed.jwtAauthentification.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    private String accessToken;
    private String username;
    private List<String> role;
}
