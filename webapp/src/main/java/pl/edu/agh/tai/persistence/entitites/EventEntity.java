package pl.edu.agh.tai.persistence.entitites;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.edu.agh.tai.DateTimeSerializer;
import pl.edu.agh.tai.model.Category;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class EventEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime dateTime;
    private String hashtag;
    @ManyToOne
    private PlaceEntity location;
    @ElementCollection(targetClass = Category.class)
    private Set<Category> categories;
    private boolean tickets;
    @ManyToMany
    private Set<UserEntity> observators;

    public EventEntity() {
    }

    public EventEntity(String name, LocalDateTime dateTime, String hashtag, PlaceEntity location, Set<Category> categories, boolean tickets) {
        this.name = name;
        this.dateTime = dateTime;
        this.hashtag = hashtag;
        this.location = location;
        this.categories = categories;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getHashtag() {
        return hashtag;
    }

    public PlaceEntity getLocation() {
        return location;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public boolean isTickets() {
        return tickets;
    }

    public Set<UserEntity> getObservators() {
        return observators;
    }

    public long getId() {
        return id;
    }
}

