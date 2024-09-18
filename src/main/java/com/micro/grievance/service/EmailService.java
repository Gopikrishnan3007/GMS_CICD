package com.micro.grievance.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final String fromEmail = "noreply@yourdomain.com";

    public void sendEmail(String to, String subject, String text) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        // Add the tracking pixel URL
        String trackingPixelUrl = "http://localhost:8006/trackingPixel?email=" + to + "&timestamp=" + System.currentTimeMillis();
        helper.setText(text + "<img src=\"" + trackingPixelUrl + "\" alt=\"\" style=\"display:none;\">", true);

        javaMailSender.send(mimeMessage);
    }
}
