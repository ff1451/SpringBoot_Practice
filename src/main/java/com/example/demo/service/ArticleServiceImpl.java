package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.domain.Board;
import com.example.demo.domain.Member;
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
    public Article createArticle(Article article) {
        article.setWriteDate(LocalDateTime.now());
        article.setUpdatedDate(LocalDateTime.now());
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
        article.setUpdatedDate(LocalDateTime.now());
        return articleRepository.updateArticle(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteArticle(id);
    }

    @Override
    public String getBoardName(Long boardID){
        Board board = boardRepository.findBoardById(boardID)
                .orElseThrow(() -> new IllegalArgumentException("게시판 id 조회 실패: " + boardID));
        return board.getBoardName();
    }

    @Override
    public String getAuthorName(Long authorId) {
        Member member = memberRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("작성자 id 조회 실패: " + authorId));
        return member.getName();
    }
}
