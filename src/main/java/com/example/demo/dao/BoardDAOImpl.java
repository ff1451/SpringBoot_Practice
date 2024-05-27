package com.example.demo.dao;

import com.example.demo.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class BoardDAOImpl implements BoardDAO {
    private final JdbcTemplate jdbcTemplate;

    public BoardDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Board> boardRowMapper = (resultSet, rowNum) -> new Board(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

    @Override
    public List<Board> getAll() {
        String sql = "select * from board";
        return jdbcTemplate.query(sql, boardRowMapper);
    }

    @Override
    public Optional<Board> getById(Long id) {
        String sql = "select * from board where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, boardRowMapper, id));
    }
}