package com.jwt.authservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenService jwtTokenService;

    public AuthController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        System.out.println("Login request: " + request);
        /*
         * Simple hardcoded login for demo.
         * In real project, validate username/password from DB.
         */
        if ("manish".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            String token = jwtTokenService.generateToken(request.getUsername());
            return new LoginResponse(token);
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }
}