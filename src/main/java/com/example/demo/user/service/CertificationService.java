package com.example.demo.user.service;

import org.springframework.stereotype.Service;

import com.example.demo.user.service.port.MailSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificationService{

    private final MailSender mailSender;
    
//    @Override
    public void send(String email, Long userId ,String certificationCode) {
    	String certificationUrl = generateCertificationUrl(userId, certificationCode);
    	String title = "Please certify your email address";
    	String content = "Please click the following link to certify your email address: " + certificationUrl;
        mailSender.send(email, title, content);
    }

//    @Override
    public String generateCertificationUrl(Long userId, String CertificationCode) {
        return "http://localhost:8080/api/users/" + userId + "/verify?certificationCode=" + CertificationCode;
    }
}
