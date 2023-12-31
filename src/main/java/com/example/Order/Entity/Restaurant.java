package com.example.Order.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private boolean hasLicense;

    private String address;

    private String cuisine;

    // TODO: 6/27/23 going to be file
    private String license;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "restaurant_id")
    private Set<Dish> dishes = new HashSet<>();

}
