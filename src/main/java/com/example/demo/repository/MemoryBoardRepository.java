package com.example.demo.repository;

import com.example.demo.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MemoryBoardRepository implements BoardRepository {
    private static final Map<Long, Board> boardStore = new HashMap<>();
    private static long currentBoardId = 0L;

    @Override
    public Board createBoard(Board board) {
        board.setId(++currentBoardId);
        boardStore.put(board.getId(), board);
        return board;
    }

    @Override
    public Optional<Board> findBoardById(Long id) {
        return Optional.ofNullable(boardStore.get(id));
    }

    @Override
    public List<Board> findAllBoards() {
        return new ArrayList<>(boardStore.values());
    }

    @Override
    public Board updateBoard(Board board) {
        boardStore.put(board.getId(), board);
        return board;

    }

    @Override
    public void deleteBoard(Long id) {
        boardStore.remove(id);
    }
}
