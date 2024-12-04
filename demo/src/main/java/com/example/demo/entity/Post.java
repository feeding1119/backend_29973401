package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private int views;

    @Column(nullable = false)
    private boolean hasFile;

    private LocalDateTime createdAt;

    @Builder
    public Post(String title, User user, boolean hasFile, LocalDateTime createdAt) {
        this.title = title;
        this.user = user;
        this.hasFile = hasFile;
        this.createdAt = createdAt;
        this.views = 0;
    }

    public void incrementViews() {
        this.views += 1;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

}
