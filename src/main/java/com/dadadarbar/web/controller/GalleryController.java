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

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/gallery")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GalleryController {

    private final GalleryService galleryService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<List<Gallery>>> uploadMultipleImages(
            @RequestParam("files") List<MultipartFile> files,  @RequestParam("year") Integer year) throws IOException {

        List<Gallery> galleries =
                galleryService.uploadMultipleImages(files,year);

        return ResponseEntity.ok(
                ApiResponse.<List<Gallery>>builder()
                        .success(true)
                        .message("File(s) Uploaded successfully")
                        .data(galleries)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        galleryService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
