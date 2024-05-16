package etc;

public class Board {
    private int boardId;
    private String boardName;

    public Board(int boardId, String boardName) {
        this.boardId = boardId;
        this.boardName = boardName;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
