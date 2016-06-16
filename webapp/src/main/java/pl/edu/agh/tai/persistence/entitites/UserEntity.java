package pl.edu.agh.tai.persistence.entitites;

import pl.edu.agh.tai.model.Role;

import javax.persistence.*;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity() {
    }

    public UserEntity(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public long getId() {
        return id;
    }
}
