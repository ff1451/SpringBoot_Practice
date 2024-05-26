package com.example.demo.domain;

import java.time.LocalDateTime;

public class Article {
    private Long articleId;
    private String articleTitle;
    private String articleContent;
    private Long authorId;
    private Long boardId;
    private LocalDateTime writeDate;
    private LocalDateTime updatedDate;

    public Article(String articleTitle, String articleContent, Long authorId, Long boardId, LocalDateTime writeDate) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.authorId = authorId;
        this.boardId = boardId;
        this.writeDate = writeDate;
    }

    public Article(long id, String title, String content, long authorId, long boardId, LocalDateTime createdDate) {
    }


    public Long getArticleId() {
        return articleId;
    }

    public void setId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Long getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    public Long getBoardId() {
        return boardId;
    }
    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
    public LocalDateTime getWriteDate() {
        return writeDate;
    }
    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}

