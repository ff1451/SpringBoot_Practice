package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemoryBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository = new MemoryBoardRepository();

    public Board createBoard(Board board) {
        return boardRepository.createBoard(board);
    }

    public Board getBoardById(Long id) {
        return boardRepository.findBoardById(id)
                .orElseThrow(()->new IllegalArgumentException("게시판 id 조회 실패: "+ id));
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAllBoards();
    }

    public Board updateBoard(Board board) {
        if (board.getId() == null) {
            throw new IllegalArgumentException("게시판 id 조회 실패");
        }
        return boardRepository.updateBoard(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteBoard(id);
    }
}
