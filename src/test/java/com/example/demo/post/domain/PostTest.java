package com.example.demo.post.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

public class PostTest {

	@Test
	public void CreatedDto로_게시글을_만들_수_있다() {
		//given
		PostCreateDto postCreateDto = PostCreateDto.builder()
				.writerId(1)
				.content("helloWorld")
				.build();
		
		User writer = User.builder()
				.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.status(UserStatus.ACTIVE)
				.build();
		//when
		Post post = Post.from(writer, postCreateDto);
		//then
		assertThat(post.getContent()).isEqualTo("helloWorld");
		assertThat(post.getWriter().getEmail()).isEqualTo("ssehn9327@gmail.com");
		assertThat(post.getWriter().getNickname()).isEqualTo("ssehn9327");
		assertThat(post.getWriter().getAddress()).isEqualTo("Deajeon");
		assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
		assertThat(post.getWriter().getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
	}
	
	@Test
	public void UpsateDto로_게시글을_수정할_수_있다() {
		//given
		
		//when
		
		//then
		
	}
}
