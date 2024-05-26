package com.example.demo.controller;

import java.util.List;

import org.springframework.ui.Model;
import com.example.demo.dto.ArticleResponse;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final ArticleService articleService;

    public PostController(ArticleService articleService) {
        this.articleService = articleService;
    }
/*
    @GetMapping("/posts")
    public String getAllArticles(Model model) {
        List<ArticleResponse> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("boardName", "자유게시판");
        return "articles";
    }
*/
@GetMapping("/posts")
public String getArticlesByBoardId(@RequestParam("boardId") Long boardId, Model model) {
    List<ArticleResponse> articles = articleService.getArticlesByBoardId(boardId);
    model.addAttribute("articles", articles);
    model.addAttribute("boardName", "자유게시판"); // Board name should be dynamically retrieved
    return "articles";
}
}
