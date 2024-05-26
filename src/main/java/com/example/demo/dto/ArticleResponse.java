package com.example.demo.dto;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long articleId,
        String articleTitle,
        String articleContent,
        String authorName,
        String boardName,
        LocalDateTime date
) {
    public static ArticleResponse of(Article article,Member member,Board board) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getArticleTitle(),
                article.getArticleContent(),
                member.getName(),
                board.getBoardName(),
                article.getWriteDate()
        );
    }
}
