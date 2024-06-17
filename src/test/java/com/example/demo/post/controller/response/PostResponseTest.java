package com.example.demo.post.controller.response;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo.post.domain.Post;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

public class PostResponseTest {

	@Test
	public void Post로_PostResponse객체를_생성할_수_있다() {
		//given
		Post post = Post.builder()
				.content("helloWorld")
				.writer(User.builder()
						.email("ssehn9327@gmail.com")
						.nickname("ssehn9327")
						.address("Deajeon")
						.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
						.status(UserStatus.ACTIVE)
						.build())
				.build();
		//when
		PostResponse postResponse = PostResponse.from(post);
		//then
		assertThat(postResponse.getContent()).isEqualTo("helloWorld");
		assertThat(postResponse.getWriter().getEmail()).isEqualTo("ssehn9327@gmail.com");
		assertThat(postResponse.getWriter().getNickname()).isEqualTo("ssehn9327");
		assertThat(postResponse.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);

	}
}
