package com.example.demo.service;

import com.example.demo.domain.Article;

import java.util.List;

public interface ArticleService {
    Article createArticle(Article article);
    Article getArticleById(Long id);
    List<Article> getAllArticles();
    Article updateArticle(Article article);
    void deleteArticle(Long id);
}
