package com.example.Order.Controller;

import com.example.Order.Dao.RestaurantRepo;
import com.example.Order.Dao.UserRepo;
import com.example.Order.Entity.Restaurant;
import com.example.Order.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    //Get all users
    @GetMapping("/user/all")
    public List<User> showAll() {
        return this.userRepo.findAll();
    }

    //get user by id
    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return this.userRepo.findById(id);
    }

    //get user by email
    @GetMapping("/user/find/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return this.userRepo.getUserByEmail(email);
    }

    //post user
    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return this.userRepo.save(user);
    }


}
