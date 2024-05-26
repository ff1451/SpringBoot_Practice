package com.example.demo.dao;

import com.example.demo.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    Article save(Article article);
    Optional<Article> findById(Long id);
    List<Article> findAll();
    Article update(Long id, Article article);
    void deleteById(Long id);
}
