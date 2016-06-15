package pl.edu.agh.tai.entities;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PostEntity {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private EventEntity event;
    private LocalDateTime publishDate;
    @OneToMany
    private List<CommentEntity> comments;
//    @OneToOne
//    private UserEntity admin;

    public PostEntity() {
    }

    public PostEntity(EventEntity event, LocalDateTime publishDate, List<CommentEntity> comments) {
        this.event = event;
        this.publishDate = publishDate;
        this.comments = comments;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

}
