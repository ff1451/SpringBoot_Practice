package com.example.demo.service;

import com.example.demo.dao.ArticleDAO;
import com.example.demo.dao.BoardDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
import com.example.demo.dto.ArticleRequest;
import com.example.demo.dto.ArticleResponse;
import com.example.demo.dto.ArticleUpdateRequest;
import com.example.demo.exception.ArticleNotFoundException;
import com.example.demo.exception.BoardNotFoundException;
import com.example.demo.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleDAO articleDAO;
    private final BoardDAO boardDAO;
    private final MemberDAO memberDAO;

    public ArticleService(ArticleDAO articleDAO, BoardDAO boardDAO, MemberDAO memberDAO) {
        this.articleDAO = articleDAO;
        this.boardDAO = boardDAO;
        this.memberDAO = memberDAO;
    }

    @Transactional
    public ArticleResponse create(ArticleRequest request) {
        Article article = new Article(
                request.title(),
                request.content(),
                request.author_id(),
                request.board_id(),
                LocalDateTime.now()
        );

        Article created = articleDAO.create(article);
        Member member = memberDAO.getById(created.getWriterId())
                .orElseThrow(() -> new MemberNotFoundException("회원 조회 실패"));
        Board board = boardDAO.getById(created.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException("게시판 조회 실패"));
        return ArticleResponse.of(created, member, board);
    }

    public ArticleResponse getById(Long id) {
        Article article = articleDAO.getById(id)
                .orElseThrow(() -> new ArticleNotFoundException("게시글 조회 실패"));
        Member member = memberDAO.getById(article.getWriterId())
                .orElseThrow(() -> new MemberNotFoundException("회원 조회 실패"));
        Board board = boardDAO.getById(article.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException("게시판 조회 실패"));
        return ArticleResponse.of(article, member, board);
    }

    public List<ArticleResponse> getAll() {
        List<Article> articles = articleDAO.getAll();
        return articles.stream()
                .map(article -> {
                    Member member = memberDAO.getById(article.getWriterId())
                            .orElseThrow(() -> new MemberNotFoundException("회원 조회 실패"));
                    Board board = boardDAO.getById(article.getBoardId())
                            .orElseThrow(() -> new BoardNotFoundException("게시판 조회 실패"));
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }


    @Transactional
    public ArticleResponse updateArticle(Long id, ArticleUpdateRequest request) {
        Article article = articleDAO.getById(id)
                .orElseThrow(() -> new ArticleNotFoundException("게시글 조회 실패"));
        article.setModifiedDate(LocalDateTime.now());
        article.update(request.board_id(), request.title(), request.content());
        Article updated = articleDAO.update(id, article);
        Member member = memberDAO.getById(updated.getWriterId())
                .orElseThrow(() -> new MemberNotFoundException("회원 조회 실패"));
        Board board = boardDAO.getById(updated.getBoardId())
                .orElseThrow(() -> new BoardNotFoundException("게시판 조회 실패"));
        return ArticleResponse.of(updated, member, board);
    }


    @Transactional
    public void deleteArticle(Long id) {
        articleDAO.delete(id);
    }

    public List<ArticleResponse> getArticlesByBoardId(Long boardId) {
        List<Article> articles = articleDAO.getByBoardId(boardId);
        return articles.stream()
                .map(article -> {
                    Member member = memberDAO.getById(article.getWriterId())
                            .orElseThrow(() -> new MemberNotFoundException("회원 조회 실패"));
                    Board board = boardDAO.getById(article.getBoardId())
                            .orElseThrow(() -> new BoardNotFoundException("게시판 조회 실패"));
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }

    public Board getBoardById(Long boardId) {
        return boardDAO.getById(boardId)
                .orElseThrow(() -> new BoardNotFoundException("게시판 조회 실패"));
    }
}

