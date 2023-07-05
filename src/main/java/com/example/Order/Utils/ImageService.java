package com.example.Order.Utils;

import com.example.Order.Dao.ImageRepo;
import com.example.Order.Dao.RestaurantRepo;
import com.example.Order.Entity.ImageData;
import com.example.Order.Entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    public void uploadImage(MultipartFile file, int restaurantId) throws IOException {
        ImageData imageData = imageRepo.save(ImageData.builder()
                .restId(restaurantId)
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.decompressImage(file.getBytes())).build());
//        Optional<Restaurant> rest = this.restaurantRepo.findById(restaurantId);
//        rest.get().setImageName(file.getOriginalFilename());
//        this.restaurantRepo.save(rest.get());
//        imageData.setRestaurant(rest.get());
        if (imageData != null ){
            System.out.println("Image uploaded Successfully");
        }

    }

    public byte[] downloadImage(Long restaurantId){
        Optional<ImageData> dbImageData = imageRepo.findByRestId(restaurantId);
        if (dbImageData.isPresent()) {
            System.out.println("SUCCESS: Image present" );
            return ImageUtils.decompressImage(dbImageData.get().getImageData());
        }
        System.out.println("ERROR: Image not present" );
        return new byte[0];
    }

    public String getImageName(Long fileId){
        Optional<ImageData> dbImageData = imageRepo.findById(fileId);
        return dbImageData.get().getName();
    }
}
