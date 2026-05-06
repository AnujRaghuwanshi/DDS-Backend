package com.dadadarbar.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GalleryYearResponse {
    private Integer year;
    private String thumbnailUrl;
    private Long totalImages;
}
