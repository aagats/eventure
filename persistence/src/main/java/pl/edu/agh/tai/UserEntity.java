package pl.edu.agh.tai;

import javax.persistence.*;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(String username, Role role) {
        this.username = username;
        this.role = role;
    }
}
