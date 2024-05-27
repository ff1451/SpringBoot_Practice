package com.example.demo.dto;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        Long author_id,
        Long board_id,
        String title,
        String content,
        LocalDateTime created_date,
        LocalDateTime modified_date
) {
    public static ArticleResponse of(Article article,Member member,Board board) {
        return new ArticleResponse(
                article.getId(),
                article.getWriterId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedDate(),
                article.getModifiedDate()
        );
    }
}
