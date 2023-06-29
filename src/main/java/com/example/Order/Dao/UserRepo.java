package com.example.Order.Dao;

import com.example.Order.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> getUserByEmail(String email);
}
