package com.example.demo.dao;

import com.example.demo.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    Article createArticle(Article article);
    Optional<Article> getArticleById(Long id);
    List<Article> getAllArticles();
    Article updateArticle(Long id, Article article);
    void deleteArticle(Long id);
    List<Article> getArticlesByBoardId(Long boardId);
}
