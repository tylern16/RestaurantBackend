package com.example.Order.Controller;

import com.example.Order.Dao.RestaurantRepo;
import com.example.Order.Dao.UserRepo;
import com.example.Order.Entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/restaurant/all")
    public List<Restaurant> showAll() {
        return this.restaurantRepo.findAll();
    }

    @GetMapping("/restaurant/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable int id) {
        return this.restaurantRepo.findById(id);
    }

    @PostMapping("/restaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant order) {
        return this.restaurantRepo.save(order);
    }

    @PutMapping("/restaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant order) {
        return this.restaurantRepo.save(order);
    }

    @DeleteMapping("/restaurant/{id}")
    public void deleteRestaurantByID(@PathVariable int id) {
        this.restaurantRepo.deleteById(id);
    }
}
