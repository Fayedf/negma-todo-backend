package com.negma.Controller;

import com.negma.DTO.Auth.AuthResponse;
import com.negma.DTO.Auth.LoginRequest;
import com.negma.DTO.Auth.RegisterRequest;
import com.negma.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class AuthController {

   private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> Register( @Valid @RequestBody RegisterRequest registerRequest) {


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.Register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login( @Valid @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.Login(loginRequest));
    }

}
