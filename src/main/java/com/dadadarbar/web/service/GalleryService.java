package com.dadadarbar.web.service;

import com.dadadarbar.web.entity.Gallery;
import com.dadadarbar.web.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final CloudinaryService cloudinaryService;

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    public Gallery uploadImage(MultipartFile file, Integer year) {

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
}
