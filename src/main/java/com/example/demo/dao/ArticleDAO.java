package com.example.demo.dao;

import com.example.demo.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    Article create(Article article);
    Optional<Article> getById(Long id);
    List<Article> getAll();
    Article update(Long id, Article article);
    void delete(Long id);
    List<Article> getByBoardId(Long id);
}
