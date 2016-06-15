package pl.edu.agh.tai.persistence.entitites;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CommentEntity {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private UserEntity author;
    private LocalDateTime dateTime;
    private String content;
    @ManyToOne
    private PostEntity post;

    public CommentEntity() {
    }

    public CommentEntity(String content, LocalDateTime dateTime, PostEntity post) {
        this.content = content;
        this.dateTime = dateTime;
        this.post = post;
    }
}
