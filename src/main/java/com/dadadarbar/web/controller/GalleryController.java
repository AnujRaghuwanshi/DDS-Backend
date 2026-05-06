package com.dadadarbar.web.controller;

import com.dadadarbar.web.entity.Gallery;
import com.dadadarbar.web.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/gallery")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GalleryController {

    private final GalleryService galleryService;

    @PostMapping("/upload")
    public ResponseEntity<Gallery> uploadGalleryImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("year") Integer year
    ) {
        Gallery savedGallery = galleryService.uploadImage(file, year);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedGallery);
    }
}
