package com.example.demo.service;

import com.example.demo.dto.PostContentResponseDto;
import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostListResponseDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.PostContent;
import com.example.demo.entity.User;
import com.example.demo.repository.PostContentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostContentRepository postContentRepository;

    public PostContentResponseDto getPostContent(Long id){
        PostContent postContent = postContentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

        Post post = postContent.getPost();

        post.incrementViews();

        postRepository.save(post);

        return new PostContentResponseDto(post.getTitle(),postContent.getContent(),postContent.getCreatedAt(),post.getViews(),post.getUser().getUserId());
    }

    public void updatePost(Long id, String title, String content, String userId, MultipartFile file) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

        PostContent postContent = postContentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));


        post.updateTitle(title);
        postRepository.save(post);

        postContent.updateContent(content);
        postContentRepository.save(postContent);

    }

    public PostListResponseDto getList(int page, int size, String searchQuery) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Post> posts;

        if(searchQuery == null) {
            posts =  postRepository.findAll(pageable);
        }else{
            posts = postRepository.searchPosts(searchQuery, pageable);
        }

        List<PostDto> postDtos = posts.getContent().stream()
                .map(post -> new PostDto(
                        post.getId(),
                        post.getUser().getUserId(),
                        post.getTitle(),
                        post.getViews(),
                        post.getCreatedAt().toString(),
                        post.isHasFile()
                ))
                .collect(Collectors.toList());


        return new PostListResponseDto(postDtos, posts.getTotalPages());
    }

    @Transactional
    public void writePost(String title, String content,String userId,MultipartFile file){

        User user = userRepository.findByUserId(userId).
                orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));
        boolean hasFile = false;
        if(file != null) hasFile = true;

        LocalDateTime currentDateTime = LocalDateTime.now();

        Post post = Post.builder()
                .title(title)
                .user(user)
                .hasFile(hasFile)
                .createdAt(currentDateTime)
                .build();

        postRepository.save(post);

        PostContent postContent = PostContent.builder()
                .content(content)
                .user(user)
                .createdAt(currentDateTime)
                .post(post)
                .build();

        postContentRepository.save(postContent);


    }
}
