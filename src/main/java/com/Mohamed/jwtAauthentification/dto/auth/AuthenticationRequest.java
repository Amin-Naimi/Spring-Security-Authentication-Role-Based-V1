package com.Mohamed.jwtAauthentification.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    private String userName;
    private String password;
}
