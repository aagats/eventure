package pl.edu.agh.tai;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class CommentEntity {

    @Id
    @GeneratedValue
    private long id;
//    @OneToOne
//    private CustomUser author;
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
