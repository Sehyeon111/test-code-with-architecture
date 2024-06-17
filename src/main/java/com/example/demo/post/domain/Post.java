package com.example.demo.post.domain;

import java.time.Clock;

import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {

    private Long id;

    private String content;

    private Long createdAt;

    private Long modifiedAt;

    private User writer;

    
    @Builder
	public Post(Long id, String content, Long createdAt, Long modifiedAt, User writer) {
		super();
		this.id = id;
		this.content = content;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.writer = writer;
	}

    public static Post from(User user,PostCreateDto postCreateDto, ClockHolder clockHolder) {
    	return Post.builder()
    			.content(postCreateDto.getContent())
    			.createdAt(clockHolder.milis())
    			.writer(user)
    			.build();
    }
    
    public Post update(PostUpdateDto postUpdateDto, ClockHolder clockHolder) {
    	return Post.builder()
    			.id(id)
    			.content(postUpdateDto.getContent())
    			.createdAt(createdAt)
    			.modifiedAt(clockHolder.milis())
    			.writer(writer)
    			.build();	
    }


}
