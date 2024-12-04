package com.example.demo.dto;

import com.example.demo.entity.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostListResponseDto {

    private List<PostDto> posts;
    private int totalPages;

    public PostListResponseDto(List<PostDto> posts, int totalPages) {
        this.posts = posts;
        this.totalPages = totalPages;
    }
}
