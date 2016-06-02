package pl.edu.agh.tai;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CommentEntity {

    @Id
    @GeneratedValue
    private long id;
//    @OneToOne
//    private CustomUser author;
    private String content;
//    private LocalDateTime time;
    @ManyToOne
    private PostEntity post;

    public CommentEntity() {
    }

    public CommentEntity(String content, PostEntity post) {
        this.content = content;
        this.post = post;
    }
}
