package com.example.Order.Dao;

import com.example.Order.Entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepo extends JpaRepository<Dish, Long> {
}
