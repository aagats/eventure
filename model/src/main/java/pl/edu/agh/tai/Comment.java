package pl.edu.agh.tai;

import java.time.LocalDateTime;

public class Comment {

    private long id;
    private CustomUser author;
    private String content;
    private LocalDateTime time;

    public Comment(long id, CustomUser author, String content, LocalDateTime time) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CustomUser getAuthor() {
        return author;
    }

    public void setAuthor(CustomUser author) {
        this.author = author;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
