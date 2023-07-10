package com.example.Order.Controller;

import com.example.Order.Dao.ImageRepo;
import com.example.Order.Entity.ImageData;
import com.example.Order.Utils.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

import static org.apache.tomcat.util.http.fileupload.IOUtils.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    ImageRepo imageRepo;

    @PostMapping("/upload/{restaurantId}")
    public ResponseEntity<?> uploadImage(@PathVariable int restaurantId, @RequestParam("image") MultipartFile file) throws IOException {
        imageService.uploadImage(file, restaurantId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/file/{fileId}")
    public ResponseEntity<?> downloadImage (@PathVariable Long fileId){
        //Optional<ImageData> image = imageRepo.findByRestId(fileId);
        byte[] imageData = imageService.downloadImage(fileId);
        //String imageName = imageRepo.findByRestId(fileId).get().getName();
        return new ResponseEntity<>(
                imageRepo.findByRestId(fileId),
                HttpStatus.OK);
    }

    //not working
    @GetMapping("/filename/{fileId}")
    public String downloadImageName (@PathVariable Long fileId){
        return imageService.getImageName(fileId);
    }
}
