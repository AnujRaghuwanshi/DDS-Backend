package com.dadadarbar.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GalleryImageResponse {
    private Long id;
    private String thumbnailUrl;
}
