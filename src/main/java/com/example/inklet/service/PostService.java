package com.example.inklet.service;

import com.example.inklet.dto.PostDTO;
import com.example.inklet.entity.Post;
import com.example.inklet.entity.User;
import com.example.inklet.repository.LikeRepository;
import com.example.inklet.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + id));
    }
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }



    public List<PostDTO> getPostsForUser(User user) {
        return postRepository.findByUser(user).stream()
                .map(post -> new PostDTO(
                        post.getId(),
                        post.getContent(),
                        post.getUser().getFirstName() + " " + post.getUser().getLastName(),
                        post.getTimestamp(),
                        likeRepository.countByPost(post),
                        false // We'll override this in controller with actual like status
                ))
                .collect(Collectors.toList());
    }
}