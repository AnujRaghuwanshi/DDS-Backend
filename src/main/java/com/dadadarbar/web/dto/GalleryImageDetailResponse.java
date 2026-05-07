package com.dadadarbar.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GalleryImageDetailResponse {

    private Long id;
    private String imageUrl;
    private Integer year;
}
