package com.negma.Service;

import com.negma.DTO.Auth.AuthResponse;
import com.negma.DTO.Auth.LoginRequest;
import com.negma.DTO.Auth.RegisterRequest;
import com.negma.Entity.User;
import com.negma.Enums.Role;
import com.negma.Repository.UserRepo;
import com.negma.Security.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    final PasswordEncoder passwordEncoder;
    final UserRepo userRepo;
    final JwtService jwtService;
    final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse Register(RegisterRequest registerRequest) {

        String email = registerRequest.getEmail();

        if (userRepo.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);

    }

    @Transactional
    public AuthResponse Login(LoginRequest loginRequest) {

        String email = loginRequest.getEmail();

        try {
            Authentication authentication =  authenticationManager.
                    authenticate(UsernamePasswordAuthenticationToken.unauthenticated(email,loginRequest.getPassword()));

            String token = jwtService.generateToken(authentication.getName());

            return new AuthResponse(token);
        } catch (AuthenticationException exception) {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

    }

}
