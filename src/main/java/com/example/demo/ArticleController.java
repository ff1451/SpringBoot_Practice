package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import etc.Article;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private Map<Integer, Article> articleMap = new HashMap<>();
    private int currentId = 1;

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable int id) {
        Article article = articleMap.get(id);
        if (article != null) {
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        article.setId(currentId);
        articleMap.put(currentId, article);
        currentId++;
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable int id, @RequestBody Article updatedArticle) {
        Article article = articleMap.get(id);
        if (article != null) {
            article.setTitle(updatedArticle.getTitle());
            article.setContent(updatedArticle.getContent());
            articleMap.put(id, article);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable int id) {
        if (articleMap.containsKey(id)) {
            articleMap.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}