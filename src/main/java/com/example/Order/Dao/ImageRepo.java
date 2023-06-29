package com.example.Order.Dao;

import com.example.Order.Entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String fileName);
}
