package pl.edu.agh.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.tai.EventureApplication;
import pl.edu.agh.tai.model.Role;
import pl.edu.agh.tai.persistence.UserRepository;
import pl.edu.agh.tai.persistence.entitites.UserEntity;

import java.security.Principal;

@RestController
public class SecurityController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(path = "/api/userauth")
    public UserEntity isUserLoggedIn(Principal principal) {
        if (principal != null) {
            String username = EventureApplication.getPrincipalUsername(principal);
            UserEntity userEntity = userRepository.findByUsername(username);
            if (userEntity == null) {
                userEntity = userRepository.save(new UserEntity(username, Role.BASIC));
                return userEntity;
            }
            if (userEntity.getRole().name().equals("ADMIN") && !((OAuth2Authentication) principal).getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                Authentication auth = new UsernamePasswordAuthenticationToken(((OAuth2Authentication) principal).getUserAuthentication().getPrincipal(), ((OAuth2Authentication) principal).getCredentials(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
                ((UsernamePasswordAuthenticationToken) auth).setDetails(((OAuth2Authentication) principal).getUserAuthentication().getDetails());
                OAuth2Authentication oauth = new OAuth2Authentication(((OAuth2Authentication) principal).getOAuth2Request(), auth);
                oauth.setDetails(((OAuth2Authentication) principal).getDetails());
                oauth.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(oauth);
            }
            return userEntity;
        }
        return null;
    }

}
