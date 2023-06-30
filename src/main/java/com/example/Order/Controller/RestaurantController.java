package com.example.Order.Controller;

import com.example.Order.Dao.RestaurantRepo;
import com.example.Order.Dao.UserRepo;
import com.example.Order.Entity.Dish;
import com.example.Order.Entity.Restaurant;
import com.example.Order.Entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestaurantController {

      //ROUTES
//    view all restarants
//    view restaurants by user id
//    get restaruant by id
//    post restaurant to specific user
//    delete restaurant by id
//    put restaurant by id


    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private UserRepo userRepo;

    //VIEW ALL restaurants
    @GetMapping("/restaurant/all")
    public List<Restaurant> showAll() {
        return this.restaurantRepo.findAll();
    }

    //view restaurant by id
    @GetMapping("/restaurant/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable int id) {
        return this.restaurantRepo.findById(id);
    }

    //get a users's resataurants by id
    @GetMapping("/user/{userId}/restaurant")
    public ResponseEntity<List<Restaurant>> getAllRestaruantsByUserId(@PathVariable(value = "userId") int userId) throws Exception {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new Exception("Not found Tutorial with id = " + userId));

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.addAll(user.getRestaurants());

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    //add a restaurant by userid and restaurant
    @PostMapping("/user/{userId}/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@PathVariable(value = "userId") int userId,
                                                       @RequestBody Restaurant newRestaurant) throws Exception {
        Restaurant restaurant = userRepo.findById(userId).map(user -> {
            user.getRestaurants().add(newRestaurant);
            return restaurantRepo.save(newRestaurant);
        }).orElseThrow(() -> new Exception("Not found User with id = " + userId));

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    //delete restaurant by id
    @DeleteMapping("/restaurant/{id}")
    public void deleteRestaurantByID(@PathVariable int id) {
        this.restaurantRepo.deleteById(id);
    }

    //update restaurant by id
    @PutMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") int id, @RequestBody Restaurant updatedRestaurant) throws Exception {
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new Exception("Restaurant " + id + "not found"));

        restaurant.setName(updatedRestaurant.getName());

        return new ResponseEntity<>(restaurantRepo.save(restaurant), HttpStatus.OK);
    }






    //OLD ROUTES
    @PostMapping("/restaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant order) {
        return this.restaurantRepo.save(order);
    }

    @PutMapping("/restaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant order) {
        return this.restaurantRepo.save(order);
    }


}
