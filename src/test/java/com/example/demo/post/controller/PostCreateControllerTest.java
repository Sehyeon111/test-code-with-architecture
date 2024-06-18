package com.example.demo.post.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.mock.TestClockHolder;
import com.example.demo.mock.TestContainer;
import com.example.demo.post.controller.response.PostResponse;
import com.example.demo.post.domain.PostCreateDto;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;

public class PostCreateControllerTest {


    @Test
    void 사용자는_게시물을_작성할_수_있다() throws Exception {
        // given
        TestContainer testContainer = TestContainer.builder()
        		.clockHolder(new TestClockHolder(1678530673958L))
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
        
        PostCreateDto postCreate = PostCreateDto.builder()
        		.writerId(1L)
        		.content("helloWorld")
        		.build();
        // when
        ResponseEntity<PostResponse> result = testContainer.postCreateController.createPost(postCreate);
        
        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
        assertThat(result.getBody().getWriter().getNickname()).isEqualTo("ssehn9327");
        assertThat(result.getBody().getContent()).isEqualTo("helloWorld");
    }
}
