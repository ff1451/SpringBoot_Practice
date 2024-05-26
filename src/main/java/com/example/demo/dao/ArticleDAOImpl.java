package com.example.demo.dao;

import com.example.demo.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleDAOImpl implements ArticleDAO{

    private final JdbcTemplate jdbcTemplate;

    public ArticleDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Article> articleRowMapper = (resultSet, rowNum) -> new Article(
            resultSet.getLong("id"),
            resultSet.getString("title"),
            resultSet.getString("content"),
            resultSet.getLong("author_id"),
            resultSet.getLong("board_id"),
            resultSet.getObject("created_date", LocalDateTime.class)
    );


    @Override
    public Article save(Article article) {
        String sql = "INSERT INTO article (title, content, author_id, board_id, created_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getArticleTitle(),article.getArticleContent(),article.getAuthorId(),article.getBoardId(),article.getWriteDate());
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        try {
            Article article = jdbcTemplate.queryForObject(sql, articleRowMapper, id);
            return Optional.ofNullable(article);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql, articleRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Article update(Long id, Article article) {
        String sql = "UPDATE article SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getArticleTitle(), article.getArticleContent(), article.getArticleId());
        return null;
    }
}
