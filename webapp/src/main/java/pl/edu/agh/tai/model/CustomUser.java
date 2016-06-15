package pl.edu.agh.tai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@JsonIgnoreProperties({"password", "authorities", "role"})
public class CustomUser extends User {

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    @Id
    public String getUsername() {
        return  super.getUsername();
    }

    @Transient
    public String getPassoword() {
        return super.getPassword();
    }
}
