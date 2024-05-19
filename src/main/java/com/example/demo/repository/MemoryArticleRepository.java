package com.example.demo.repository;

import com.example.demo.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;


@Repository
public class MemoryArticleRepository implements ArticleRepository {
    private static final Map<Long,Article> articleStore = new HashMap<>();
    private static long currentArticleId = 0L;

    @Override
    public Article createArticle(Article article) {
        article.setId(++currentArticleId);
        articleStore.put(article.getArticleId(),article);
        return article;
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        return Optional.ofNullable(articleStore.get(id));
    }

    @Override
    public List<Article> findAllArticles() {
        return new ArrayList<>(articleStore.values());
    }

    @Override
    public Article updateArticle(Article article) {
        articleStore.put(article.getArticleId(),article);
        return article;
    }

    @Override
    public void deleteArticleById(Long id) {
        articleStore.remove(id);
    }
}
