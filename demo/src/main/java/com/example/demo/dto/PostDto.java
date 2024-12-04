package com.example.demo.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String userId;
    private String title;
    private int views;
    private String createdAt;
    private boolean hasFile;


    public PostDto(Long id,String userId,String title,int views,String createdAt,boolean hasFile){
        this.id = id;
        this.userId =userId;
        this.title = title;
        this.views = views;
        this.createdAt = createdAt;
        this.hasFile = hasFile;
    }
}
