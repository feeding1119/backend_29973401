package com.example.demo.controller;

import com.example.demo.dto.PostContentResponseDto;
import com.example.demo.dto.PostListResponseDto;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/list")
    public ResponseEntity<PostListResponseDto> getPosts(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String searchQuery) {

        return ResponseEntity.ok(postService.getList(page,size,searchQuery));
    }

    @PostMapping("/write")
    public ResponseEntity<Void> writePost( @RequestParam String title,
                                            @RequestParam String content,
                                            @RequestParam String userId,
                                            @RequestParam(required = false) MultipartFile file) {
        postService.writePost(title, content, userId,file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/content")
    public ResponseEntity<PostContentResponseDto> getPostContent(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostContent(id));
    }

    @PutMapping("/{id}/content")
    public ResponseEntity<Void> updatePost(@PathVariable Long id,
                                           @RequestParam String title,
                                           @RequestParam String content,
                                           @RequestParam String userId,
                                           @RequestParam(required = false) MultipartFile file) {
        postService.updatePost(id, title, content, userId, file);
        return ResponseEntity.ok().build();
    }
}
