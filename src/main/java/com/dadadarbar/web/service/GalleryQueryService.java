package com.dadadarbar.web.service;

import com.dadadarbar.web.dto.GalleryImageDetailResponse;
import com.dadadarbar.web.dto.GalleryImageResponse;
import com.dadadarbar.web.dto.GalleryYearResponse;
import com.dadadarbar.web.entity.Gallery;
import com.dadadarbar.web.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryQueryService {

    private final GalleryRepository repository;

    public List<GalleryYearResponse> getGalleryYears() {

        return repository.fetchGalleryYears()
                .stream()
                .map(g -> GalleryYearResponse.builder()
                        .year(g.getYear())
                        .thumbnailUrl(g.getThumbnailUrl())
                        .totalImages(g.getTotalImages())
                        .build())
                .toList();
    }


    public Page<GalleryImageResponse> getImagesByYear(
            Integer year,
            int page,
            int size
    ) {

        Page<Gallery> galleryPage =
                repository.findByYearOrderByCreatedAtDesc(
                        year,
                        PageRequest.of(page, size)
                );

        return galleryPage.map(g ->
                GalleryImageResponse.builder()
                        .id(g.getId())
                        .thumbnailUrl(g.getThumbnailUrl())
                        .build()
        );
    }

    public GalleryImageDetailResponse getImageById(Long id) {

        Gallery gallery = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Image not found"));

        return GalleryImageDetailResponse.builder()
                .id(gallery.getId())
                .imageUrl(gallery.getImageUrl())
                .year(gallery.getYear())
                .build();
    }
}
