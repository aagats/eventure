package pl.edu.agh.tai.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.tai.Event;
import pl.edu.agh.tai.Place;
import pl.edu.agh.tai.Post;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @RequestMapping(path="api/posts/{id}", method = RequestMethod.GET)
    public Post showPost(@PathVariable(value="id") int id) {
        Event event1 = new Event(1, "Juwenalia", new Place(1, "AGH", "Kraków", "MS", 0), null, false);
        event1.setHashtag("flowers");

        return new Post(event1, null, 1, null, null);
    }

    @RequestMapping(path="api/posts", method = RequestMethod.GET)
    public List<Post> showPosts() throws IOException {
        List<Post> posts = new ArrayList<>();
        Event event1 = new Event(1, "Juwenalia", new Place(1, "AGH", "Kraków", "MS", 0), null, false);
        event1.setHashtag("flowers");
        Event event2 = new Event(2, "Open'er", new Place(2, "Lotnisko", "Gdynia", "brak", 12), null, true);
        event2.setHashtag("globe");

        posts.add(new Post(event1, null, 1, null, null));
        posts.add(new Post(event2, null, 2, null, null));

        return posts;
    }
}
