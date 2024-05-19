package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemoryArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository = new MemoryArticleRepository();

    @Override
    public Article createArticle(Article article) {
        return articleRepository.createArticle(article);
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물 id 조회 실패: "+ id));
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAllArticles();
    }

    @Override
    public Article updateArticle(Article article) {
        if(article.getArticleId()==null) {
            throw new IllegalArgumentException("게시물 id 조회 실패");
        }
        return articleRepository.updateArticle(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteArticle(id);
    }
}
