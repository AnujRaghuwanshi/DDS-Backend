package com.dadadarbar.web.validation;

import com.dadadarbar.web.exception.FileUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class FileValidation {

    public void validateImage(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new FileUploadException("File is empty");
        }

        BufferedImage image = ImageIO.read(file.getInputStream());

        if (image == null) {
            throw new FileUploadException("Uploaded file is not a valid image.");
        }

        String filename = file.getOriginalFilename();

        if (filename == null || filename.isBlank()) {
            throw new FileUploadException("Invalid filename.");
        }

        String contentType = file.getContentType();

        if (contentType == null ||
                (!contentType.equals("image/jpeg")
                        && !contentType.equals("image/png")
                        && !contentType.equals("image/webp"))) {

            throw new FileUploadException(
                    "Only JPG, PNG and WEBP images are allowed."
            );
        }
    }
}
