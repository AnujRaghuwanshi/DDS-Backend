package com.dadadarbar.web.service;

import com.dadadarbar.web.dto.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendContactMessage(ContactRequest req){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("anujraghuwanshi147@gmail.com");
        mail.setReplyTo(req.getEmail());
        mail.setSubject("New contact request from Website");
        mail.setText(
                """
                Name: %s
                Email: %s
                Message: %s
                """
                        .formatted(req.getName(),
                                req.getEmail(),
                                req.getMessage())
        );
        javaMailSender.send(mail);
    }
}
