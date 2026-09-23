package com.coverwell.crm.controller;

import com.coverwell.crm.model.User;
import com.coverwell.crm.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = authService.login(
                request.email(),
                request.password()
        );

        if (user == null) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");
        }

        return ResponseEntity.ok(user);
    }

    public record LoginRequest(
            String email,
            String password
    ) {
    }
}