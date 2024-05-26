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

    private final RowMapper<Board> boardRowMapper = (resultSet, rowNum)-> new Board(
        resultSet.getLong("id"),
        resultSet.getString("name")
    );

    @Override
    public Board createBoard(Board board) {
        String sql = "INSERT INTO board (name) VALUES (?)";
        jdbcTemplate.update(sql, board.getBoardName());
        return board;
    }

    @Override
    public Optional<Board> getBoardById(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        try {
            Board board = jdbcTemplate.queryForObject(sql, new Object[]{id}, boardRowMapper);
            return Optional.of(board);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Board> getAllBoards() {
        String sql = "SELECT * FROM board";
        return jdbcTemplate.query(sql, boardRowMapper);
    }

    @Override
    public Board updateBoard(Board board) {
        String sql = "UPDATE board SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, board.getBoardName(), board.getBoardId());
        return board;
    }

    @Override
    public void deleteBoard(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
