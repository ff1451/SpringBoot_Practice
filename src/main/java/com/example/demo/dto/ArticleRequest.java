package com.example.demo.dto;

public record ArticleRequest(
        String title,
        String content,
        Long author_id,
        Long board_id
) {

}
