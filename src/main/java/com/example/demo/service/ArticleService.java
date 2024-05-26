package com.example.demo.service;

import com.example.demo.dto.ArticleRequest;
import com.example.demo.dto.ArticleResponse;

import java.util.List;

public interface ArticleService {
    ArticleResponse createArticle(ArticleRequest articleRequest);
    ArticleResponse getArticleById(Long id);
    List<ArticleResponse> getAllArticles();
    ArticleResponse updateArticle(Long id, ArticleRequest articleRequest);
    void deleteArticle(Long id);

    List<ArticleResponse> getArticlesByBoardId(Long boardId);
}
