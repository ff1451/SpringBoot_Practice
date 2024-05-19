package com.example.demo.service;

import com.example.demo.domain.Board;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board);
    Board getBoardById(Long id);
    List<Board> getAllBoards();
    Board updateBoard(Board board);
    void deleteBoard(Long id);
}
