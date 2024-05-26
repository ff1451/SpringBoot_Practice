package com.example.demo.service;

import com.example.demo.dao.ArticleDAO;
import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.dto.ArticleRequest;
import com.example.demo.dto.ArticleResponse;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDAO articleDAO;
    private final BoardDAO boardDAO;
    private final MemberDAO memberDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO, BoardDAO boardDAO, MemberDAO memberDAO) {
        this.articleDAO = articleDAO;
        this.boardDAO = boardDAO;
        this.memberDAO = memberDAO;
    }

    @Override
    public ArticleResponse createArticle(ArticleRequest request) {
        Article article = new Article(
                request.articleTitle(),
                request.articleContent(),
                request.authorId(),
                request.boardId(),
                LocalDateTime.now()
        );
        articleDAO.createArticle(article);
        Member member = memberDAO.getMemberById(article.getArticleId())
                .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
        Board board = boardDAO.getBoardById(article.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
        return ArticleResponse.of(article, member, board);
    }

    @Override
    public ArticleResponse getArticleById(Long id) {
        Article article = articleDAO.getArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물 id 조회 실패: " + id));
        Member member = memberDAO.getMemberById(article.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
        Board board = boardDAO.getBoardById(article.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
        return ArticleResponse.of(article, member, board);
    }

    @Override
    public List<ArticleResponse> getAllArticles() {
        List<Article> articles = articleDAO.getAllArticles();
        return articles.stream()
                .map(article -> {
                    Member member = memberDAO.getMemberById(article.getAuthorId())
                            .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
                    Board board = boardDAO.getBoardById(article.getBoardId())
                            .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }

    @Override
    public ArticleResponse updateArticle(Long id, ArticleRequest request) {
        Article article = articleDAO.getArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 조회 실패"));

        article.setArticleTitle(request.articleTitle());
        article.setArticleContent(request.articleContent());
        article.setAuthorId(request.authorId());
        article.setBoardId(request.boardId());
        article.setUpdatedDate(LocalDateTime.now());

        Article updatedArticle = articleDAO.updateArticle(id, article);
        Member member = memberDAO.getMemberById(updatedArticle.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
        Board board = boardDAO.getBoardById(updatedArticle.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
        return ArticleResponse.of(updatedArticle, member, board);
    }

    @Override
    public void deleteArticle(Long id) {
        articleDAO.deleteArticle(id);
    }

    @Override
    public List<ArticleResponse> getArticlesByBoardId(Long boardId) {
        List<Article> articles = articleDAO.getArticlesByBoardId(boardId);
        return articles.stream()
                .map(article -> {
                    Member member = memberDAO.getMemberById(article.getAuthorId())
                            .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
                    Board board = boardDAO.getBoardById(article.getBoardId())
                            .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }
}

