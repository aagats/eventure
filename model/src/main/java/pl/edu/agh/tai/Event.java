package pl.edu.agh.tai;

import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Event {

    private String name;
    private LocalDateTime date;
    private String hashtag;
    private Place location;
    private Set<Category> categories;
    private boolean tickets;
    private Set<User> observators;

    public Event() {
    }

    public Event(String name, Place location, Set<Category> categories, boolean tickets) {
        this.name = name;
        this.location = location;
        this.categories = categories;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Place getLocation() {
        return location;
    }

    public void setLocation(Place location) {
        this.location = location;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public boolean isTickets() {
        return tickets;
    }

    public void setTickets(boolean tickets) {
        this.tickets = tickets;
    }

    public Set<User> getObservators() {
        return observators;
    }

    public void setObservators(Set<User> observators) {
        this.observators = observators;
    }

    public void addObservator(User user) {
        observators.add(user);
    }

    public void deleteObservator(User user) {
        observators.remove(user);
    }

}
