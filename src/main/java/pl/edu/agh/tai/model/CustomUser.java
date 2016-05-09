package pl.edu.agh.tai.model;

import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {

    private Collection<Role> role;

    public CustomUser(String username, String password, Collection<Role> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<Role> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Collection<Role> getRole() {
        return role;
    }

    public void setRole(Collection<Role> role) {
        this.role = role;
    }
}
