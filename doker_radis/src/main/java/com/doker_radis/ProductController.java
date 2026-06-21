package com.doker_radis;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public List<String> getProducts() {
        return List.of("Laptop", "Mobile", "Keyboard");
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProductById(@PathVariable Long id) {
        return Map.of(
                "id", id,
                "name", "Laptop",
                "price", 65000
        );
    }

    @PostMapping
    public Map<String, String> createProduct(@RequestBody Map<String, Object> request) {
        return Map.of(
                "message", "Product created successfully",
                "productName", String.valueOf(request.get("name"))
        );
    }
}