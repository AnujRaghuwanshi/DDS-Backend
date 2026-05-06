package com.dadadarbar.web.controller;

import com.dadadarbar.web.dto.GalleryYearResponse;
import com.dadadarbar.web.service.GalleryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
