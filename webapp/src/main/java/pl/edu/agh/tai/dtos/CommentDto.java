package pl.edu.agh.tai.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentDto {

//    private final long user;
    private final String content;

    @JsonCreator

    public CommentDto(@JsonProperty(value = "content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
