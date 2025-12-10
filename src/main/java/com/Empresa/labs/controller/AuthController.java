package com.Empresa.labs.controller;

import com.Empresa.labs.dto.LoginRequestDTO;
import com.Empresa.labs.dto.LoginResponseDTO;
import com.Empresa.labs.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // ajusta si usas otro front
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO resp = authService.login(request);
        return ResponseEntity.ok(resp);
    }
}
