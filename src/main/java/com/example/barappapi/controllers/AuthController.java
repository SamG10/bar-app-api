package com.example.barappapi.controllers;

import com.example.barappapi.dto.AuthenticationRequest;
import com.example.barappapi.dto.AuthenticationResponse;
import com.example.barappapi.dto.RegisterRequest;
import com.example.barappapi.models.Barmaker;
import com.example.barappapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/me")
    public ResponseEntity<Barmaker> getCurrentUser() {
        Barmaker currentUser = authService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }
}
