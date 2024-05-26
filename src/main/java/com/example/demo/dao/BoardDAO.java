package com.example.demo.dao;

import com.example.demo.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDAO {
    Board createBoard(Board board);
    Optional<Board> getBoardById(Long id);
    List<Board> getAllBoards();
    Board updateBoard(Board board);
    void deleteBoard(Long id);
}
