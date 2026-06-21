package com.jwt.orderservice;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @GetMapping("/public")
    public String publicApi() {
        return "This is public API. No token required.";
    }

    @GetMapping("/orders/my")
    public Map<String, Object> myOrders(Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        String username = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        List<String> roles = jwt.getClaimAsStringList("roles");

        return Map.of(
                "message", "JWT is valid. Access allowed.",
                "username", username,
                "email", email,
                "roles", roles,
                "orders", List.of("Order-101", "Order-102", "Order-103")
        );
    }
    @GetMapping("/token-info")
    public Map<String, Object> tokenInfo(Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();

        Instant expiryTime = jwt.getExpiresAt();
        Instant currentTime = Instant.now();

        boolean expired = expiryTime != null && expiryTime.isBefore(currentTime);

        return Map.of(
                "username", jwt.getSubject(),
                "currentTime", currentTime,
                "expiryTime", expiryTime,
                "expired", expired
        );
    }
}