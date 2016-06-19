package pl.edu.agh.tai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.edu.agh.tai.model.Role;
import pl.edu.agh.tai.persistence.UserRepository;
import pl.edu.agh.tai.persistence.entitites.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = authUser.getUsername();
        UserEntity byUsername = userRepository.findByUsername(username);
        if (byUsername.getRole() == Role.ADMIN) {
            httpServletResponse.sendRedirect("/admin");
        }
    }
}
