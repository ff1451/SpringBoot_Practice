package com.example.demo.service;

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

    private final ArticleRepository articleRepository = new MemoryArticleRepository();
    private final BoardRepository boardRepository = new MemoryBoardRepository();
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public ArticleResponse createArticle(ArticleRequest request) {
        Article article = new Article(
                request.articleTitle(),
                request.articleContent(),
                request.authorId(),
                request.boardId(),
                LocalDateTime.now()
        );
        Article createdArticle = articleRepository.createArticle(article);
        Member member = memberRepository.findById(createdArticle.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
        Board board = boardRepository.findBoardById(createdArticle.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
        return ArticleResponse.of(createdArticle, member, board);
    }

    @Override
    public ArticleResponse getArticleById(Long id) {
        Article article = articleRepository.findArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물 id 조회 실패: " + id));
        Member member = memberRepository.findById(article.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
        Board board = boardRepository.findBoardById(article.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
        return ArticleResponse.of(article, member, board);
    }

    @Override
    public List<ArticleResponse> getAllArticles() {
        List<Article> articles = articleRepository.findAllArticles();
        return articles.stream()
                .map(article -> {
                    Member member = memberRepository.findById(article.getAuthorId())
                            .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
                    Board board = boardRepository.findBoardById(article.getBoardId())
                            .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
                    return ArticleResponse.of(article, member, board);
                }).toList();
    }

    @Override
    public ArticleResponse updateArticle(Long id, ArticleRequest request) {
        Article article = articleRepository.findArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글 조회 실패"));

        article.setArticleTitle(request.articleTitle());
        article.setArticleContent(request.articleContent());
        article.setAuthorId(request.authorId());
        article.setBoardId(request.boardId());
        article.setUpdatedDate(LocalDateTime.now());

        Article updatedArticle = articleRepository.updateArticle(id, article);
        Member member = memberRepository.findById(updatedArticle.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("회원 조회 실패"));
        Board board = boardRepository.findBoardById(updatedArticle.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("게시판 조회 실패"));
        return ArticleResponse.of(updatedArticle, member, board);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteArticle(id);
    }
}

