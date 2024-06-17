package com.example.demo.user.controller.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

public class UserResponseTest {

	@Test
	public void User으로_응답을_생성할_수_있다() {
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
		UserResponse userResponse = UserResponse.from(user);
		
		//then
		assertThat(userResponse.getId()).isEqualTo(1);
		assertThat(userResponse.getEmail()).isEqualTo("ssehn9327@gmail.com");
		assertThat(userResponse.getNickname()).isEqualTo("ssehn9327");
		assertThat(userResponse.getStatus()).isEqualTo(UserStatus.ACTIVE);
		
	}
}
