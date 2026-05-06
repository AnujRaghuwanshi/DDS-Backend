package com.dadadarbar.web.service;

import com.dadadarbar.web.dto.GalleryYearResponse;
import com.dadadarbar.web.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
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
}
