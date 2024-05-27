package com.example.demo.dao;

import com.example.demo.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
    public Article create(Article article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO article (title, content, author_id, board_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setLong(3, article.getWriterId());
            ps.setLong(4, article.getBoardId());
            return ps;
            }, keyHolder);
        article.setId(keyHolder.getKey().longValue());

        return article;
    }

    @Override
    public Optional<Article> getById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        try {
            Article article = jdbcTemplate.queryForObject(sql, articleRowMapper, id);
            return Optional.ofNullable(article);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Article> getAll() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql, articleRowMapper);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Article> getByBoardId(Long id) {
        String sql = "SELECT * FROM article WHERE board_id = ?";
        return jdbcTemplate.query(sql, articleRowMapper, id);
    }

    @Override
    public Article update(Long id, Article article) {
        String sql = "UPDATE article SET board_id =?, title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getBoardId(), article.getTitle(), article.getContent(), article.getId());
        return article;
    }
}
