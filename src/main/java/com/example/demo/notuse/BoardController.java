package com.example.demo.notuse;

import com.example.demo.domain.Board;
import com.example.demo.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        Board createdBoard = boardService.createBoard(board);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        try{
            Board board = boardService.getBoardById(id);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> Boards = boardService.getAllBoards();
        return new ResponseEntity<>(Boards, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board board) {
        try {
            board.setId(id);
            Board updatedBoard = boardService.updateBoard(board);
            return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        try {
            boardService.deleteBoard(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
