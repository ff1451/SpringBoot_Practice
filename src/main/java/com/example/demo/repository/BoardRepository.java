package com.example.demo.repository;

import com.example.demo.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board createBoard(Board board);
    Optional<Board> findBoardById(Long id);
    List<Board> findAllBoards();
    Board updateBoard(Board board);
    void deleteBoard(Long id);
}
