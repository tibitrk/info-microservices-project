package com.microservices.Product_Service.controller;

import com.microservices.Product_Service.client.UserClient;
import com.microservices.Product_Service.model.Product;
import com.microservices.Product_Service.model.PurchaseResponse;
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


    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/buy/{productId}/user/{userId}")
    public ResponseEntity<PurchaseResponse> buyProduct(@PathVariable Long productId, @PathVariable Long userId) {
        try {

            UserResponse user = productService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PurchaseResponse(userId,
                        null, productId, null, 0.0));
            }


            Product product = productService.getProductById(productId);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PurchaseResponse(userId, null, productId, null, 0.0));
            }

            PurchaseResponse response = new PurchaseResponse(userId, user.getName(), productId, product.getName(), product.getPrice());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PurchaseResponse(userId,null,productId,null,0.0));
        }
    }
}

