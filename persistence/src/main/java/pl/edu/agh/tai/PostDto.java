package pl.edu.agh.tai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDto {

    private final long event;
//    private final long admin;

    @JsonCreator
    public PostDto(@JsonProperty(value = "event", required = true) long event) {
        this.event = event;
    }

    public long getEvent() {
        return event;
    }

}
