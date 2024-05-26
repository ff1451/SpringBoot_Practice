package com.example.demo.repository;

import com.example.demo.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article createArticle(Article article);
    Optional<Article> findArticleById(Long id);
    List<Article> findAllArticles();
    Article updateArticle(Long id, Article article);
    void deleteArticle(Long id);
}
