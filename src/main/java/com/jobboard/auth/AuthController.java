package com.jobboard.auth;

import com.jobboard.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// @CrossOrigin(origins = {
//         "http://localhost:3000",
//         "http://localhost:3001",
//         "https://job-hook-frontend-rtu3.vercel.app"
// })
// public class AuthController {

//     @PostMapping("/login")
//     public JwtResponse login(@RequestBody LoginRequest request) {

//         System.out.println("========== LOGIN REQUEST ==========");
//         System.out.println("Username: " + request.getUsername());
//         System.out.println("Password: " + request.getPassword());

//         if ("aditya".equals(request.getUsername())
//                 && "aditya1234".equals(request.getPassword())) {

//             System.out.println("LOGIN SUCCESS");

//             String token = JwtUtil.generateToken(
//                     request.getUsername(),
//                     "ADMIN"
//             );

//             return new JwtResponse(token);
//         }

//         System.out.println("LOGIN FAILED");

//         throw new RuntimeException("Invalid Username or Password");
//     }
//   }
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/test")
    public String test() {
        return "Backend Working";
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {

        if ("aditya".equals(request.getUsername())
                && "aditya1234".equals(request.getPassword())) {

            String token = JwtUtil.generateToken(request.getUsername(), "ADMIN");
            return new JwtResponse(token);
        }

        throw new RuntimeException("Invalid Username or Password");
    }
}
