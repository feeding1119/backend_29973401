package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostContentResponseDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private int views;
    private String userId;

    public PostContentResponseDto(String title,String content,LocalDateTime createdAt,int views,String userId){
        this.title =title;
        this.content = content;
        this.createdAt = createdAt;
        this.views = views;
        this.userId = userId;
    }
}
