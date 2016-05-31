package pl.edu.agh.tai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DumbController {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(path = "/healthcheck")
    public String lol() {
        return "lol";
    }

    @RequestMapping(path = "/places", method = RequestMethod.POST)
    public void createPlacePlace(@RequestBody PlaceDto place) {
        placeRepository.save(new PlaceEntity(place.getName(), "nyc", "wall street", 4));
        System.out.println("lol done");
    }

    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public void createEvent(@RequestBody EventDto event) {
        long locationId = event.getLocation();
        //TODO: null pointer exc
        PlaceEntity location = placeRepository.findOne(locationId);
        eventRepository.save(new EventEntity(event.getName(), event.getHashtag(), location, event.getCategories(), event.hasTickets()));
    }
}