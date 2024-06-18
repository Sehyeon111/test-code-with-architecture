package com.example.demo.post.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestContainer;
import com.example.demo.post.controller.response.PostResponse;
import com.example.demo.post.domain.Post;
import com.example.demo.post.domain.PostCreateDto;
import com.example.demo.post.domain.PostUpdateDto;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

public class PostControllerTest {

    @Test
    void 사용자는_게시물을_단건_조회_할_수_있다() {
        // given
    	TestContainer testContainer = TestContainer.builder()
    			.build();
    	User user = User.builder()
    			.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.lastLoginAt(100L)
				.status(UserStatus.ACTIVE)
    			.build();
    	testContainer.postRepository.save(Post.builder()
    			.id(1L)
    			.content("helloWorld")
    			.createdAt(0L)
    			.writer(user)
    			.build());
        // when
    	ResponseEntity<PostResponse> result = testContainer.postController.getPostById(1L);
        // then
    	assertThat(result.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
    	assertThat(result.getBody().getId()).isEqualTo(1);
    	assertThat(result.getBody().getContent()).isEqualTo("helloWorld");
    	assertThat(result.getBody().getWriter().getNickname()).isEqualTo("ssehn9327");
       
    }

    @Test
    void 사용자가_존재하지_않는_게시물을_조회할_경우_에러가_난다() {
        // given
    	TestContainer testContainer = TestContainer.builder()
    			.build();
    	testContainer.userRepository.save(User.builder()
    			.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.lastLoginAt(100L)
				.status(UserStatus.ACTIVE)
    			.build());
        // when
        // then
    	assertThatThrownBy(() -> {
    		testContainer.postController.getPostById(1L);
    	}).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void 사용자는_게시물을_수정할_수_있다() {
        // given
    	TestContainer testContainer = TestContainer.builder()
    			.clockHolder(new TestClockHolder(1678530673958L))
    			.build();
    	User user = User.builder()
    			.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.lastLoginAt(100L)
				.status(UserStatus.ACTIVE)
    			.build();
    	testContainer.userRepository.save(user);
    	testContainer.postRepository.save(Post.builder()
    			.content("helloWorld")
				.writer(user)
    			.build());
    	
    	PostUpdateDto postUpdateDto = PostUpdateDto.builder()
    			.content("HelloWorld :)")
    			.build();
    	
    	// when
    	ResponseEntity<PostResponse> result = testContainer.postController.updatePost(1L, postUpdateDto);
    	
    	//then
    	assertThat(result.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
    }
}
