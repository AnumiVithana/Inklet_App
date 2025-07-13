package com.example.inklet.repository;

import com.example.inklet.entity.Like;
import com.example.inklet.entity.Post;
import com.example.inklet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    Long countByPost(Post post);
    void deleteByUserAndPost(User user, Post post);
}