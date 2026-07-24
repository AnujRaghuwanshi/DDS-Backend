package com.dadadarbar.web.service;

import com.dadadarbar.web.entity.Gallery;
import com.dadadarbar.web.exception.FileUploadException;
import com.dadadarbar.web.repository.GalleryRepository;
import com.dadadarbar.web.validation.FileValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final CloudinaryService cloudinaryService;
    private final FileValidation fileValidation;

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    public Gallery uploadImage(MultipartFile file, Integer year) throws IOException {

        if (file.isEmpty()) {
            throw new FileUploadException("File is empty");
        }

        fileValidation.validateImage(file);

        Map<String, Object> uploadResult = cloudinaryService.uploadFile(file, year);

        String publicId = uploadResult.get("public_id").toString();

        String imageUrl =
                "https://res.cloudinary.com/" + cloudName +
                        "/image/upload/f_auto,q_auto,w_1600/" + publicId;

        String thumbnailUrl =
                "https://res.cloudinary.com/" + cloudName +
                        "/image/upload/f_auto,q_auto,w_500/" + publicId;

        boolean coverExists =
                galleryRepository.existsByYearAndIsCoverTrue(year);

        Gallery gallery = Gallery.builder()
                .year(year)
                .publicId(publicId)
                .imageUrl(imageUrl)
                .thumbnailUrl(thumbnailUrl)
                .isCover(!coverExists)
                .createdAt(LocalDateTime.now())
                .build();

        return galleryRepository.save(gallery);
    }


    @Transactional
    public void deleteImage(Long id) {

        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Image not found"));

        Integer year = gallery.getYear();
        boolean wasCover = gallery.getIsCover();

        // Delete from Cloudinary
        cloudinaryService.deleteFile(gallery.getPublicId());

        // Delete from DB
        galleryRepository.delete(gallery);

        // Reassign cover if needed
        if (wasCover) {

            galleryRepository
                    .findFirstByYearOrderByCreatedAtDesc(year)
                    .ifPresent(newCover -> {

                        newCover.setIsCover(true);
                        galleryRepository.save(newCover);
                    });
        }
    }

    @Transactional
    public List<Gallery> uploadMultipleImages(List<MultipartFile> files,Integer year) throws IOException {

        if (files == null || files.isEmpty()) {
            throw new FileUploadException(
                    "No files provided"
            );
        }
        List<Gallery> uploadedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            Gallery gallery = uploadImage(file, year);
            uploadedImages.add(gallery);
        }

        return uploadedImages;
    }
}
