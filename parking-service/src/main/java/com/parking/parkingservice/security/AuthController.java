package com.parking.parkingservice.security;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // تعريف Map المستخدمين
    private final Map<String, String> users = new HashMap<>();

    // تهيئة المستخدمين عند بدء التطبيق
    @PostConstruct
    public void init() {
        users.put("admin", passwordEncoder.encode("admin123"));
        users.put("user", passwordEncoder.encode("user123"));
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (users.containsKey(username) &&
            passwordEncoder.matches(password, users.get(username))) {

            String token = jwtUtil.generateToken(username);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", username);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Username ou password incorrect");
    }
}