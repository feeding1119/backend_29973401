package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class PostContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =  65535)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @Builder
    public PostContent(String content, User user, LocalDateTime createdAt, Post post) {
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
        this.post = post;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}