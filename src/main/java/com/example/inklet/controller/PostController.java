package com.example.inklet.controller;

import com.example.inklet.dto.PostDTO;
import com.example.inklet.entity.Post;
import com.example.inklet.entity.User;
import com.example.inklet.service.LikeService;
import com.example.inklet.service.PostService;
import com.example.inklet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;

    @GetMapping("/home")
    public String showHome(Model model, Authentication auth) {
        String email = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.findByEmail(email).orElseThrow();

        List<PostDTO> posts = postService.getPostsForUser(user).stream()
                .peek(p -> {
                    Post post = new Post();
                    post.setId(p.getId());
                    p.setLikedByUser(likeService.isPostLikedByUser(user, post));
                })
                .collect(Collectors.toList());

        model.addAttribute("post", new Post());
        model.addAttribute("posts", posts);
        return "home";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute Post post, Authentication auth) {
        String email = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.findByEmail(email).orElseThrow();

        post.setUser(user);
        postService.createPost(post);
        return "redirect:/home";
    }

    @DeleteMapping("/post/{id}")
    @ResponseBody
    public String deletePost(@PathVariable Long id, Authentication auth) {
        String email = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.findByEmail(email).orElseThrow();
        Post post = postService.getPostById(id);

        if (post.getUser().getId().equals(user.getId())) {
            postService.deletePost(id);
            return "deleted";
        } else {
            return "unauthorized";
        }
    }

}

