package pl.edu.agh.tai.persistence.entitites;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.edu.agh.tai.DateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PostEntity {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private EventEntity event;
    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime publishDate;
    @OneToMany
    private List<CommentEntity> comments;
    @OneToOne
    private UserEntity admin;

    public PostEntity() {
    }

    public PostEntity(EventEntity event, LocalDateTime publishDate, UserEntity admin) {
        this.event = event;
        this.publishDate = publishDate;
        this.comments = new ArrayList<>();
        this.admin = admin;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public long getId() {
        return id;
    }

    public UserEntity getAdmin() {
        return admin;
    }
}
