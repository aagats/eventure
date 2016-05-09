package pl.edu.agh.tai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.tai.model.Event;
import pl.edu.agh.tai.model.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @RequestMapping(path="api/events", method = RequestMethod.GET)
    public List<Event> showTasks() throws IOException {
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, "Juwe", new Place(1, "AGH", "Kraków", "MS", 0), null, false));
        events.add(new Event(2, "Open'er", new Place(2, "Lotnisko", "Gdynia", "brak", 12), null, true));

        return events;
    }

}