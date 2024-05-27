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
    public ResponseEntity<ArticleResponse> getArticle(
            @PathVariable Long id) {
        try {
            ArticleResponse article = articleService.getById(id);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<ArticleResponse>> getArticlesByBoardId(
            @RequestParam(name="boardId",required = false) Long boardId) {
        if (boardId != null) {
            List<ArticleResponse> articles = articleService.getArticlesByBoardId(boardId);
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } else {
            List<ArticleResponse> articles = articleService.getAll();
            return new ResponseEntity<>(articles, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(
            @RequestBody ArticleRequest request) {
        ArticleResponse response = articleService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleUpdateRequest request) {
        try{
            ArticleResponse response = articleService.updateArticle(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        try {
            articleService.deleteArticle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}