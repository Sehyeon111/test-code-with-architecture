package com.example.demo.user.controller.port;

public interface CertificationService {

	void send(String email, Long id, String certificationCode);
	
	String generateCertificationUrl(Long userId, String CertificationCode);

}
