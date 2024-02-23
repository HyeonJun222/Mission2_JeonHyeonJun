package com.example.shopping.Security.Repository;

import com.example.shopping.Security.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (String username);
    boolean existsByUsername(String username);
}
