package com.example.inklet.controller;

import com.example.inklet.entity.Post;
import com.example.inklet.entity.User;
import com.example.inklet.service.LikeService;
import com.example.inklet.service.PostService;
import com.example.inklet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final PostService postService;
    private final UserService userService;

    @PostMapping("/like/{postId}")
    @ResponseBody
    public boolean toggleLike(@PathVariable Long postId, Authentication auth) {
        String email = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.findByEmail(email).orElseThrow();
        Post post = postService.getPostById(postId);

        return likeService.toggleLike(user, post); // returns true if liked, false if unliked
    }

}