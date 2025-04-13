package com.quiz.Start.service;

import com.quiz.Start.model.Post;
import com.quiz.Start.model.User;
import com.quiz.Start.repository.PostRepository;
import com.quiz.Start.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final FileStorageService fileStorageService;

    public Post createPost(String caption, MultipartFile image) {
        String imagePath = fileStorageService.storeFile(image);

        Post post = Post.builder()
                .caption(caption)
                .imagePath(imagePath)
                .build();

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}