package com.example.demo.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.controller.port.PostService;
import com.example.demo.post.controller.response.PostResponse;
import com.example.demo.post.domain.PostUpdateDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Tag(name = "게시물(posts)")
@Builder
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable long id) {
        return ResponseEntity
            .ok()
            .body(PostResponse.from(postService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable long id, @RequestBody PostUpdateDto postUpdateDto) {
        return ResponseEntity
            .ok()
            .body(PostResponse.from(postService.update(id, postUpdateDto)));
    }

}