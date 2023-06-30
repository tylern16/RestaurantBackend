package com.example.Order.Dao;

import com.example.Order.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String fileName);
}
