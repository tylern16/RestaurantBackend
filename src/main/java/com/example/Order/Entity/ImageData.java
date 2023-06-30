package com.example.Order.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;


    private int restId;

}

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
