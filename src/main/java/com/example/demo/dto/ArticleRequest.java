package com.example.demo.dto;

public record ArticleRequest(
        String articleTitle,
        String articleContent,
        Long authorId,
        Long boardId
) {

}
