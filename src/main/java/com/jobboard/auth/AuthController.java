package com.jobboard.auth;

import com.jobboard.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {

        if ("aditya".equals(request.getUsername())
                && "aditya1234".equals(request.getPassword())) {

            String token = JwtUtil.generateToken(
                    request.getUsername(),
                    "ADMIN"
            );

            return new JwtResponse(token);
        }

        throw new RuntimeException("Invalid Username or Password");
    }
}