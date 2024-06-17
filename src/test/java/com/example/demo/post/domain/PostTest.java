package com.example.demo.post.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.mock.FakePostRepository;
import com.example.demo.mock.TestClockHolder;
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
		
		ClockHolder clockHolder = new TestClockHolder(1678530673958L);
		//when
		Post post = Post.from(writer, postCreateDto, clockHolder);
		//then
		assertThat(post.getContent()).isEqualTo("helloWorld");
		assertThat(post.getWriter().getEmail()).isEqualTo("ssehn9327@gmail.com");
		assertThat(post.getWriter().getNickname()).isEqualTo("ssehn9327");
		assertThat(post.getWriter().getAddress()).isEqualTo("Deajeon");
		assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
		assertThat(post.getWriter().getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
	}
	
	@Test
	public void UpdateDto로_게시글을_수정할_수_있다() {
		//given
		PostUpdateDto postUpdate = PostUpdateDto.builder()
				.content("hello world :)")
				.build();
		
		User writer = User.builder()
				.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.status(UserStatus.ACTIVE)
				.build();
		
		Post post = Post.builder()
				.id(1L)
    			.content("helloworld")
    			.createdAt(1678530673958L)
    			.modifiedAt(0L)
    			.writer(writer)
				.build();
		
		ClockHolder clockHolder = new TestClockHolder(1678530673958L);
		
		//when
		post = post.update(postUpdate, clockHolder);
		
		//then
		assertThat(post.getContent()).isEqualTo("hello world :)");
		
	}
}
