package com.dadadarbar.web.controller;

import com.dadadarbar.web.dto.GalleryImageDetailResponse;
import com.dadadarbar.web.dto.GalleryImageResponse;
import com.dadadarbar.web.dto.GalleryYearResponse;
import com.dadadarbar.web.service.GalleryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GalleryPublicController {

    private final GalleryQueryService service;

    @GetMapping("/years")
    public List<GalleryYearResponse> getYears() {
        return service.getGalleryYears();
    }

    @GetMapping("/{year}")
    public Page<GalleryImageResponse> getImages(
            @PathVariable Integer year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "24") int size
    ) {
        return service.getImagesByYear(year, page, size);
    }

    @GetMapping("/image/{id}")
    public GalleryImageDetailResponse getImage(@PathVariable Long id) {
        return service.getImageById(id);
    }
}
