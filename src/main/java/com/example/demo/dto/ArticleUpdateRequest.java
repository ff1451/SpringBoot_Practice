package com.example.demo.dto;

public record ArticleUpdateRequest(
        Long board_id,
        String title,
        String content
) {
}
