package com.negma.DTO.Auth;

import com.negma.Enums.Role;

import java.util.UUID;

public class AuthResponse {


   private String accessToken ;

    private UUID userId ;
    private String fullName ;
    private String email ;
    private Role role ;

}
