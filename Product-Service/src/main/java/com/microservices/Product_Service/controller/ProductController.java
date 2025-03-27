package com.microservices.Product_Service.controller;

import com.microservices.Product_Service.client.UserClient;
import com.microservices.Product_Service.model.Product;
import com.microservices.Product_Service.model.UserResponse;
import com.microservices.Product_Service.repository.ProductRepository;
import com.microservices.Product_Service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserClient userClient;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/buy/{productId}/user/{userId}")
    public ResponseEntity<String> buyProduct(@PathVariable Long productId, @PathVariable Long userId) {
        try {
            // Fetch user details from User Service
            UserResponse user = userClient.getUserById(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
            }

            // Fetch product from DB
            Product product = productRepository.findById(productId).orElse(null);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
            }

            return ResponseEntity.ok("User " + user.getName() + " bought the product: " + product.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
