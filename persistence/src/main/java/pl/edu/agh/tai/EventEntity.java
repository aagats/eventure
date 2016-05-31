package pl.edu.agh.tai;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EventEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    //    private LocalDateTime date;
    private String hashtag;
    @ManyToOne
    private PlaceEntity location;
    @ElementCollection(targetClass = Category.class)
    private Set<Category> categories;
    private boolean tickets;

    public EventEntity() {
    }

    public EventEntity(String name, String hashtag, PlaceEntity location, Set<Category> categories, boolean tickets) {
        this.name = name;
        this.hashtag = hashtag;
        this.location = location;
        this.categories = categories;
        this.tickets = tickets;
    }
}

