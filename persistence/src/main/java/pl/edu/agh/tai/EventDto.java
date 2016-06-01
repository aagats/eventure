package pl.edu.agh.tai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class EventDto {
    private final String name;
    //TODO: fight jackson annotations to support some exact date format or use DateTimeFormatter manually
//    private final LocalDateTime date;
    private final String hashtag;
    private final long location;
    private final Set<Category> categories;
    private final boolean tickets;
    //TODO: observators in db
//    private final List<CustomUser> observators;

    @JsonCreator
    public EventDto(@JsonProperty(value = "name", required = true) String name,
                    @JsonProperty("hashtag") String hashtag,
                    @JsonProperty("location") long location,
                    @JsonProperty("categories") Set<Category> categories,
                    @JsonProperty("tickets") boolean tickets) {
        this.name = name;
        this.hashtag = hashtag;
        this.location = location;
        this.categories = categories;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public String getHashtag() {
        return hashtag;
    }

    public long getLocation() {
        return location;
    }

    public boolean hasTickets() {
        return tickets;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
