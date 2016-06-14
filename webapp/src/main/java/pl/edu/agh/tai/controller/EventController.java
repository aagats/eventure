package pl.edu.agh.tai.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.agh.tai.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController

public class EventController {

    private Event event = new Event(1, "Juwenalia", new Place(1, "AGH", "Kraków", "MS", 0), null, false);

    @RequestMapping("api/events/{id}")
    public Event showEvent(@PathVariable(value = "id") int id){
        event.setHashtag("flowers");
        Set<Category> categories = new HashSet<>();
        categories.add(Category.PARTY);
        categories.add(Category.MUSIC);
        event.setCategories(categories);

        return event;
    }

    @RequestMapping("photos/{tag}")
    public String getPhotosFromTag(@PathVariable(value = "tag") String tag, Principal principal) {
        OAuth2Authentication userInfo = (OAuth2Authentication) principal;
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) userInfo.getDetails();
        String token = details.getTokenValue();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.instagram.com/v1/tags/" + tag + "/media/recent")
                .queryParam("access_token", token);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    @RequestMapping(path = "/api/events/{id}", method = RequestMethod.PUT)
    public Event addObservator(@PathVariable(value = "id") int id, Principal principal) {
        event.addObservator(new CustomUser("Ala", "pass", new ArrayList<Role>()));

        return event;
    }
}
