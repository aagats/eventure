package pl.edu.agh.tai.persistence.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.agh.tai.model.Category;

import java.util.Set;

public class EventDto {
    private final String name;
    private final String dateTime;
    private final String hashtag;
    private final long location;
    private final Set<Category> categories;
    private final boolean tickets;
    //TODO: observators in db
//    private final List<CustomUser> observators;

    @JsonCreator
    public EventDto(@JsonProperty(value = "name", required = true) String name,
                    @JsonProperty(value = "date") String dateTime,
                    @JsonProperty("hashtag") String hashtag,
                    @JsonProperty("location") long location,
                    @JsonProperty("categories") Set<Category> categories,
                    @JsonProperty("tickets") boolean tickets) {
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

    public String getDateTime() {
        return dateTime;
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
