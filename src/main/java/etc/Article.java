package etc;

import java.time.LocalDateTime;

public class Article {
    private int articleId;
    private String articleTitle;
    private String articleContent;
    private String authorId;
    private String boardId;
    private LocalDateTime writeDate;
    private LocalDateTime updatedDate;

    public Article() {
    }

    public Article(int articleId, String articleTitle, String articleContent, String authorId, String boardId,LocalDateTime writeDate) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.authorId = authorId;
        this.boardId = boardId;
        this.writeDate = writeDate;
    }

    public int getarticleId() {
        return articleId;
    }

    public void setId(int articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getBoardId() {
        return boardId;
    }
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
    public LocalDateTime getWriteDate() {
        return writeDate;
    }
    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}

