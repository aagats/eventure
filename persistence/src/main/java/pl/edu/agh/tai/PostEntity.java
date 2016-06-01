package pl.edu.agh.tai;


import javax.persistence.*;
import java.util.List;

@Entity
public class PostEntity {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private EventEntity event;
//    private LocalDateTime publishDate;
    @OneToMany
    private List<CommentEntity> comments;
//    @OneToOne
//    private UserEntity admin;

    public PostEntity() {
    }

    public PostEntity(EventEntity event) {
        this.event = event;
    }
}
