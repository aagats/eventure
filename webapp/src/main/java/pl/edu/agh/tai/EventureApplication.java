package pl.edu.agh.tai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableWebSecurity
@EnableOAuth2Sso
@RestController
public class EventureApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(EventureApplication.class, args);
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/api/userauth", "/css/**", "/js/**", "/img/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @RequestMapping(path = "/api/userauth")
    public Boolean isUserLoggedIn(Principal principal) {
        Boolean isAuth;
        isAuth = principal != null;
        return isAuth;
    }
}
