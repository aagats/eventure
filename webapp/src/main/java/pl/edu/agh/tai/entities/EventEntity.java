package pl.edu.agh.tai.entities;

import pl.edu.agh.tai.Category;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class EventEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private LocalDateTime dateTime;
    private String hashtag;
    @ManyToOne
    private PlaceEntity location;
    @ElementCollection(targetClass = Category.class)
    private Set<Category> categories;
    private boolean tickets;

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
}

