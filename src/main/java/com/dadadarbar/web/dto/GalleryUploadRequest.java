package com.dadadarbar.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GalleryUploadRequest {

    @NotNull(message = "Year is required")
    @Min(value = 2021, message = "Invalid year")
    @Max(value = 2026, message = "Invalid year")
    private Integer year;

    @NotNull(message = "File is required")
    private MultipartFile file;
}
