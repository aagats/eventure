package pl.edu.agh.tai;

import java.time.LocalDateTime;

public class Comment {

    private CustomUser author;
    private String content;
    private LocalDateTime time;

    public Comment() {
    }

    public Comment(CustomUser author, String content, LocalDateTime time) {
        this.author = author;
        this.content = content;
        this.time = time;
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
