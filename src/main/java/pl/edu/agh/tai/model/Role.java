package pl.edu.agh.tai.model;

import org.springframework.security.core.GrantedAuthority;

enum Role implements GrantedAuthority {
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
