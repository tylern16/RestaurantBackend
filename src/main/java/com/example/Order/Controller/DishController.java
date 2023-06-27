package com.example.Order.Controller;

import com.example.Order.Dao.DishRepo;
import com.example.Order.Dao.RestaurantRepo;
import com.example.Order.Entity.Dish;
import com.example.Order.Entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DishController {

    @Autowired
    private DishRepo dishRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;


    //get a restaurant's dishes by id
    @GetMapping("/restaurant/{restaurantId}/dish")
    public ResponseEntity<List<Dish>> getAllDidhesByRestaurantId(@PathVariable(value = "restaurantId") int restaurantId) throws Exception {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new Exception("Not found Tutorial with id = " + restaurantId));

        List<Dish> dishes = new ArrayList<Dish>();
        dishes.addAll(restaurant.getDishes());

        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    //get a dish by id
    @GetMapping("/dish/{id}")
    public ResponseEntity<Dish> getCommentsByTutorialId(@PathVariable(value = "id") Long id) throws Exception {
        Dish dish = dishRepo.findById(id)
                .orElseThrow(() -> new Exception("Not found Comment with id = " + id));

        return new ResponseEntity<>(dish, HttpStatus.OK);
    }

    //add a dish by restaurantid and dependent
    @PostMapping("/restaurant/{restaurantId}/dish")
    public ResponseEntity<Dish> createComment(@PathVariable(value = "restaurantId") int restaurantId,
                                                   @RequestBody Dish newDish) throws Exception {
        Dish dish = restaurantRepo.findById(restaurantId).map(restaurant -> {
            restaurant.getDishes().add(newDish);
            return dishRepo.save(newDish);
        }).orElseThrow(() -> new Exception("Not found Tutorial with id = " + restaurantId));

        return new ResponseEntity<>(dish, HttpStatus.CREATED);
    }

    //update dependent by id
    @PutMapping("/dish/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable("id") Long id, @RequestBody Dish updatedDish) throws Exception {
        Dish dish = dishRepo.findById(id)
                .orElseThrow(() -> new Exception("CommentId " + id + "not found"));

        dish.setName(updatedDish.getName());
        dish.setCost(updatedDish.getCost());

        return new ResponseEntity<>(dishRepo.save(dish), HttpStatus.OK);
    }

    //delete dependent by id
    @DeleteMapping("/dish/{id}")
    public  void deleteDependent(@PathVariable("id") Long id) {
        dishRepo.deleteById(id);
    }
}
