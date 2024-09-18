package com.micro.grievance.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro.grievance.service.EmailService;

import jakarta.mail.MessagingException;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/sendEmail")
public class MailController {

	 @Autowired
	    private EmailService emailService;

	    @PostMapping
	    public ResponseEntity<String> sendEmail(
	            @RequestParam String from,
	            @RequestParam String to,
	            @RequestParam String subject,
	            @RequestParam String body
	    ) {
	        try {
	            emailService.sendEmail(to, subject, body);
	            return ResponseEntity.ok("Email sent successfully");
	        } catch (MessagingException | IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
	        }
	    }
	    
}
