package com.dadadarbar.web.controller;

import com.dadadarbar.web.dto.ApiResponse;
import com.dadadarbar.web.dto.ContactRequest;
import com.dadadarbar.web.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> sendMessage(@RequestBody @Valid ContactRequest req){
        emailService.sendContactMessage(req);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Message sent successfully")
                .build()
        );
    }
}
