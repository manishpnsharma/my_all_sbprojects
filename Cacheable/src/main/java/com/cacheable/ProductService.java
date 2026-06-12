package com.cacheable;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class ProductService {// 1. CACHE READ: If "products" cache has the ID, return it immediately without running this method.
    @Cacheable(cacheNames = "products", key = "#id")
    public Product getProductById(Long id) {
        simulateSlowService();
        return new Product(id, "Laptop");
    }

    // 2. CACHE UPDATE: Runs the method and updates the "products" cache with the fresh value.
    @CachePut(cacheNames = "products", key = "#id")
    public Product updateProduct(Long id, String newName) {
        return new Product(id, newName);
    }

    // 3. CACHE REMOVAL: Removes the entry from "products" cache so future reads fetch fresh data.
    @CacheEvict(cacheNames = "products", key = "#id")
    public void deleteProduct(Long id) {
        // Database deletion logic goes here
    }

    // Artificial delay mimicking heavy database work
    private void simulateSlowService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}