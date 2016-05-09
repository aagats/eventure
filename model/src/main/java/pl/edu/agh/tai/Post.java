package pl.edu.agh.tai;

import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.List;

public class Post {

    private long id;
    private Event event;
    private LocalDateTime publishDate;
    private List<Comment> comments;
    private User admin;


    public Post(Event event, LocalDateTime publishDate, long id, List<Comment> comments, User admin) {
        this.event = event;
        this.publishDate = publishDate;
        this.id = id;
        this.comments = comments;
        this.admin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment);
    }

}
