package tai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.tai.Event;
import pl.edu.agh.tai.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @RequestMapping(path="api/events", method = RequestMethod.GET)
    public List<Event> showTasks() throws IOException {
        List<Event> events = new ArrayList<>();
        Event event1 = new Event(1, "Juwe", new Place(1, "AGH", "Kraków", "MS", 0), null, false);
        event1.setHashtag("juwenalia");
        events.add(event1);
        events.add(new Event(2, "Open'er", new Place(2, "Lotnisko", "Gdynia", "brak", 12), null, true));

        return events;
    }

    @RequestMapping(path="login", method = RequestMethod.GET)
    public void login(String token) {
        System.out.println(token);
    }

}