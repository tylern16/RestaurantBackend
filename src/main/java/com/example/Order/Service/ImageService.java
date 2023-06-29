package com.example.Order.Service;

import com.example.Order.Dao.ImageRepo;
import com.example.Order.Entity.ImageData;
import com.example.Order.Utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepo imageRepo;

    public void uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null ){
            System.out.println("Image uploaded Successfully");
        }

    }

    public byte[] downloadImage( String fileName){
        Optional<ImageData> dbImageData = imageRepo.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
