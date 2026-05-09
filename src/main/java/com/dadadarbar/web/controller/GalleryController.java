package com.dadadarbar.web.controller;

import com.dadadarbar.web.dto.ApiResponse;
import com.dadadarbar.web.dto.GalleryUploadRequest;
import com.dadadarbar.web.entity.Gallery;
import com.dadadarbar.web.service.GalleryService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponse<Gallery>> uploadGalleryImage(@ModelAttribute @Valid GalleryUploadRequest request
    ) {
        Gallery savedGallery = galleryService.uploadImage(
                request.getFile(),
                request.getYear()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Gallery>builder()
                        .success(true)
                        .message("Image uploaded successfully")
                        .data(savedGallery)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(
            @PathVariable Long id
    ) {
        galleryService.deleteImage(id);

        return ResponseEntity.noContent().build();
    }
}
