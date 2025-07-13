package com.example.inklet.repository;


import com.example.inklet.entity.Post;
import com.example.inklet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}