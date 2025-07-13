package com.example.inklet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime timestamp;
    private Long likeCount;
    private boolean likedByUser;
}