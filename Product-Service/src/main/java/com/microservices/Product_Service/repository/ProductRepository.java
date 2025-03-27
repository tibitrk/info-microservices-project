package com.microservices.Product_Service.repository;

import com.microservices.Product_Service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
