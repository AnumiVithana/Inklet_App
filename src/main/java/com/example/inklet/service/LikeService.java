package com.example.inklet.service;

import com.example.inklet.entity.Like;
import com.example.inklet.entity.Post;
import com.example.inklet.entity.User;
import com.example.inklet.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public boolean toggleLike(User user, Post post) {
        Optional<Like> existing = likeRepository.findByUserAndPost(user, post);
        if (existing.isPresent()) {
            likeRepository.delete(existing.get());
            return false; // unliked
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            return true; // liked
        }
    }

    public boolean isPostLikedByUser(User user, Post post) {
        return likeRepository.findByUserAndPost(user, post).isPresent();
    }

    public long countLikes(Post post) {
        return likeRepository.countByPost(post);
    }
}
