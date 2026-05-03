package com.dadadarbar.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GalleryUploadRequest {

    @NotNull
    private Integer year;

    @NotNull
    private MultipartFile file;
}
