package com.example.demo.service;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemoryBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository = new MemoryBoardRepository();

    @Override
    public Board createBoard(Board board) {
        return boardRepository.createBoard(board);
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.findBoardById(id)
                .orElseThrow(()->new IllegalArgumentException("게시판 id 조회 실패: "+ id));
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAllBoards();
    }

    @Override
    public Board updateBoard(Board board) {
        if (board.getId() == null) {
            throw new IllegalArgumentException("게시판 id 조회 실패");
        }
        return boardRepository.updateBoard(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteBoard(id);
    }
}
