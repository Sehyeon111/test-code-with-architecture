package com.example.demo.user.controller.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

public class MyProfileResponseTest {

	@Test
	public void MyProfileResponse객체로_응답할_수_있다() {
		//given
		User user = User.builder()
				.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.status(UserStatus.ACTIVE)
				.build();
		//when
		MyProfileResponse myProfileResponse = MyProfileResponse.from(user); 
		//then
		assertThat(myProfileResponse.getId()).isEqualTo(1);
		assertThat(myProfileResponse.getEmail()).isEqualTo("ssehn9327@gmail.com");
		assertThat(myProfileResponse.getNickname()).isEqualTo("ssehn9327");
		assertThat(myProfileResponse.getAddress()).isEqualTo("Deajeon");
		assertThat(myProfileResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
	}
}
