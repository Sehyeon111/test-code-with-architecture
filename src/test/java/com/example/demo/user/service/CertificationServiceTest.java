package com.example.demo.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo.mock.FakeMailSender;

public class CertificationServiceTest {

	@Test
	public void 이메일과_컨텐츠가_제대로_만들어져서_보내지는지_테스트한다() {
		//given
		FakeMailSender fakeMailSender = new FakeMailSender();
		CertificationService certificationService = new CertificationService(fakeMailSender);
		//when
		
		certificationService.send("kok202@naver.com", 1L, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
		
		//then
		assertThat(fakeMailSender.email).isEqualTo("kok202@naver.com");
		assertThat(fakeMailSender.title).isEqualTo("Please certify your email address");
//		assertThat(fakeMailSender.content).isEqualTo("Please click the following link to certify your email address: ");
		
	}
}
