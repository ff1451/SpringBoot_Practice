package etc;

public class Article {
    private int articleId;
    private String articleTitle;
    private String articleContent;
    private String authorId;
    private String boardId;
    private String writeDate;
    private String updatedDate;

    public Article() {
    }

    public Article(int articleId, String articleTitle, String articleContent, String authorId, String boardId,String writeDate) {
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
    public String getWriteDate() {
        return writeDate;
    }
    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }
    public String getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}

