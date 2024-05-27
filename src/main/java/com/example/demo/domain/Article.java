package com.example.demo.domain;

import java.time.LocalDateTime;

public class Article {
    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private Long boardId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Article(String title, String content, Long writerId, Long boardId, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.boardId = boardId;
        this.createdDate = createdDate;
    }

    public Article(long id, String title, String content, long writerId, long boardId, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.boardId = boardId;
        this.createdDate = createdDate;
    }

    public void update(Long boardId, String articleTitle, String content) {
        this.boardId = boardId;
        this.title = articleTitle;
        this.content = content;
        this.modifiedDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long articleId) {
        this.id = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getWriterId() {
        return writerId;
    }
    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }
    public Long getBoardId() {
        return boardId;
    }
    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}