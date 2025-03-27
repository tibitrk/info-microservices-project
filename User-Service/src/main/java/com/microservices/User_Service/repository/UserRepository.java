package com.microservices.User_Service.repository;

import com.microservices.User_Service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
