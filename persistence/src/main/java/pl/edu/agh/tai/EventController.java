package pl.edu.agh.tai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "api/events", method = RequestMethod.POST)
    public void createEvent(@RequestBody EventDto event) {
        long locationId = event.getLocation();
        String dateTime = event.getDateTime();
        LocalDateTime localDateTime = parseStringToLocalDateTime(dateTime);
        //TODO: null pointer exc
        PlaceEntity location = placeRepository.findOne(locationId);
        eventRepository.save(new EventEntity(event.getName(), localDateTime, event.getHashtag(), location, event.getCategories(), event.hasTickets()));
    }

//    @RequestMapping(path = "api/events", method = RequestMethod.GET)
//    public List<Event> showTasks() {
//        Iterable<EventEntity> eventRepositoryAll = eventRepository.findAll();
//        List<Event> events = new ArrayList<>();
//        for (EventEntity eventEntity : eventRepositoryAll) {
//            events.add(new Event(eventEntity.getName(), eventEntity.getDateTime(), eventEntity.getHashtag(), eventEntity.))
//        }
//    }

    private LocalDateTime parseStringToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }


}
