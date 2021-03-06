package pl.edu.agh.tai;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN {
        @Override
        public String getAuthority() {
            return "ADMIN";
        }
    }, BASIC {
        @Override
        public String getAuthority() {
            return "BASIC";
        }
    }
}
