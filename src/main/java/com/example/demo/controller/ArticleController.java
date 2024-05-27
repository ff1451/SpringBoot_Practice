package com.example.demo.controller;


import com.example.demo.dto.ArticleRequest;
import com.example.demo.dto.ArticleResponse;
import com.example.demo.dto.ArticleUpdateRequest;
import org.springframework.stereotype.Controller;

import com.example.demo.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
            ArticleResponse article = articleService.getById(id);
            return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ArticleResponse>> getArticlesByBoardId(
            @RequestParam(name="boardId",required = false) Long boardId) {
        List<ArticleResponse> articles;
        if (boardId != null) {
            articles = articleService.getArticlesByBoardId(boardId);
        } else {
            articles = articleService.getAll();
        }
        return new ResponseEntity<>(articles,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody ArticleRequest request) {
        ArticleResponse response = articleService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleUpdateRequest request) {
        ArticleResponse response = articleService.updateArticle(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}