package pl.edu.agh.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.agh.tai.EventureApplication;
import pl.edu.agh.tai.model.Event;
import pl.edu.agh.tai.model.Place;
import pl.edu.agh.tai.persistence.EventRepository;
import pl.edu.agh.tai.persistence.PlaceRepository;
import pl.edu.agh.tai.persistence.PostRepository;
import pl.edu.agh.tai.persistence.UserRepository;
import pl.edu.agh.tai.persistence.dtos.EventDto;
import pl.edu.agh.tai.persistence.entitites.EventEntity;
import pl.edu.agh.tai.persistence.entitites.PlaceEntity;
import pl.edu.agh.tai.persistence.entitites.PostEntity;
import pl.edu.agh.tai.persistence.entitites.UserEntity;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController

public class EventController {

    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

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

    @RequestMapping(path = "api/events", method = RequestMethod.POST)
    public EventEntity createEvent(@RequestBody EventDto event) {
        long locationId = event.getLocation();
        String dateTime = event.getDateTime();
        LocalDateTime localDateTime = parseStringToLocalDateTime(dateTime);
        //TODO: null pointer exc
        PlaceEntity location = placeRepository.findOne(locationId);
        return eventRepository.save(new EventEntity(event.getName(), localDateTime, event.getHashtag(), location, event.getCategories(), event.hasTickets()));
    }

    @RequestMapping(path = "api/events", method = RequestMethod.GET)
    public List<Event> showEvents() {
        Iterable<EventEntity> eventRepositoryAll = eventRepository.findAll();
        List<Event> events = new ArrayList<>();
        for (EventEntity eventEntity : eventRepositoryAll) {
            PlaceEntity placeEntity = eventEntity.getLocation();
            Place place = new Place(placeEntity.getId(), placeEntity.getName(), placeEntity.getCity(), placeEntity.getStreet(), placeEntity.getBuildingNumber());
            events.add(new Event(eventEntity.getId(), eventEntity.getName(), eventEntity.getDateTime(), eventEntity.getHashtag(), place, eventEntity.getCategories(), eventEntity.isTickets()));
        }
        return events;
    }

    @RequestMapping(path = "api/events/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable(value = "id") long id) {
        //TODO: null pointer exception
        EventEntity eventEntity = eventRepository.findOne(id);
        PlaceEntity placeEntity = eventEntity.getLocation();
        Place place = new Place(placeEntity.getId(), placeEntity.getName(), placeEntity.getCity(), placeEntity.getStreet(), placeEntity.getBuildingNumber());
        return new Event(eventEntity.getId(), eventEntity.getName(), eventEntity.getDateTime(), eventEntity.getHashtag(), place, eventEntity.getCategories(), eventEntity.isTickets());
    }

    @RequestMapping(path ="api/events/{id}/watch", method = RequestMethod.POST)
    public String watchEvent(@PathVariable(value = "id") long id, Principal principal) {
        EventEntity eventEntity = eventRepository.findOne(id);
        String username = EventureApplication.getPrincipalUsername(principal);
        UserEntity userEntity = userRepository.findByUsername(username);
        eventEntity.getObservators().add(userEntity);
        eventRepository.save(eventEntity);
        return username;
    }

    @RequestMapping(path = "api/watches", method = RequestMethod.GET)
    public List<PostEntity> showWatches(Principal principal) {
        UserEntity user = userRepository.findByUsername(EventureApplication.getPrincipalUsername(principal));
        List<EventEntity> watches = eventRepository.findByObservators(user);
        List<PostEntity> posts = new ArrayList<>();
        for (EventEntity event : watches) {
            posts.add(postRepository.findByEvent(event));
        }
        return posts;
    }

    private LocalDateTime parseStringToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
