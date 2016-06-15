package pl.edu.agh.tai.controllers;

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
import pl.edu.agh.tai.Event;
import pl.edu.agh.tai.Place;
import pl.edu.agh.tai.dtos.EventDto;
import pl.edu.agh.tai.entities.EventEntity;
import pl.edu.agh.tai.entities.PlaceEntity;
import pl.edu.agh.tai.repositories.EventRepository;
import pl.edu.agh.tai.repositories.PlaceRepository;


import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping("photos/{tag}")
    public String user(@PathVariable(value = "tag") String tag, Principal principal) {
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
    public void createEvent(@RequestBody EventDto event) {
        long locationId = event.getLocation();
        String dateTime = event.getDateTime();
        LocalDateTime localDateTime = parseStringToLocalDateTime(dateTime);
        //TODO: null pointer exc
        PlaceEntity location = placeRepository.findOne(locationId);
        eventRepository.save(new EventEntity(event.getName(), localDateTime, event.getHashtag(), location, event.getCategories(), event.hasTickets()));
    }

    @RequestMapping(path = "api/events", method = RequestMethod.GET)
    public List<Event> showTasks() {
        Iterable<EventEntity> eventRepositoryAll = eventRepository.findAll();
        List<Event> events = new ArrayList<>();
        for (EventEntity eventEntity : eventRepositoryAll) {
            PlaceEntity placeEntity = eventEntity.getLocation();
            Place place = new Place(placeEntity.getName(), placeEntity.getCity(), placeEntity.getStreet(), placeEntity.getBuildingNumber());
            events.add(new Event(eventEntity.getName(), eventEntity.getDateTime(), eventEntity.getHashtag(), place, eventEntity.getCategories(), eventEntity.isTickets()));
        }
        return events;
    }

    @RequestMapping(path = "api/events/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable(value = "id") long id) {
        //TODO: null pointer exception
        EventEntity eventEntity = eventRepository.findOne(id);
        PlaceEntity placeEntity = eventEntity.getLocation();
        Place place = new Place(placeEntity.getName(), placeEntity.getCity(), placeEntity.getStreet(), placeEntity.getBuildingNumber());
        return new Event(eventEntity.getName(), eventEntity.getDateTime(), eventEntity.getHashtag(), place, eventEntity.getCategories(), eventEntity.isTickets());
    }

    private LocalDateTime parseStringToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
