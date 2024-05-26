package com.example.demo.domain;

public class Board {
    private Long boardId;
    private String boardName;

    public Board(long id, String name) {
    }


    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
