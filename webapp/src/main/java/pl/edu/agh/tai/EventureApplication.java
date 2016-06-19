package pl.edu.agh.tai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;

@SpringBootApplication
@EnableWebSecurity
@EnableOAuth2Sso
@RestController
public class EventureApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(EventureApplication.class, args);
	}

    @Autowired
    AuthenticationSuccessHandlerImpl successHandler;

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
//                .and()
//                .formLogin()
//                .successHandler(successHandler);
    }

    @RequestMapping(path = "/api/userauth")
    public String isUserLoggedIn(Principal principal) {
        if (principal != null) {
            return getPrincipalUsername(principal);
        }
        return null;
    }

    public static String getPrincipalUsername(Principal principal) {
        return(String) ((LinkedHashMap) ((LinkedHashMap) ((OAuth2Authentication) principal).getUserAuthentication().getDetails()).get("data")).get("username");
    }
}
