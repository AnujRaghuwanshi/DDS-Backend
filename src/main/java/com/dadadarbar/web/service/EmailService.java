package com.dadadarbar.web.service;

import com.dadadarbar.web.dto.ContactRequest;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final Resend resend;

    @Async
    public void sendContactMessage(ContactRequest req){
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("DDS<onboarding@resend.dev>") // Must be verified domain on Resend
                .to("anujraghuwanshi147@gmail.com")
                .replyTo(req.getEmail())
                .subject("New contact request from Website")
                .html("""
                        <p><b>Name:</b> %s</p>
                        <p><b>Email:</b> %s</p>
                        <p><b>Message:</b></p>
                        <p>%s</p>
                        """.formatted(
                        req.getName(),
                        req.getEmail(),
                        req.getMessage()
                ))
                .build();
        try {
            CreateEmailResponse response = resend.emails().send(params);
        } catch (ResendException e) { // Catches native Resend API client exceptions
            throw new RuntimeException("Failed to send email via Resend SDK"+ e);
        }

    }
}
