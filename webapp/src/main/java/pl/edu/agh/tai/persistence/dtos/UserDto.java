package pl.edu.agh.tai.persistence.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.agh.tai.model.Role;

public class UserDto {
    private final String username;
    private final Role role;

    public UserDto(@JsonProperty(value = "username", required = true) String username,
                   @JsonProperty(value = "role", required = true) Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}