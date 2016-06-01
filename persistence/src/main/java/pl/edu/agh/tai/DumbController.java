package pl.edu.agh.tai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DumbController {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(path = "/healthcheck")
    public String lol() {
        return "lol";
    }

    @RequestMapping(path = "/places", method = RequestMethod.POST)
    public void createPlacePlace(@RequestBody PlaceDto place) {
        placeRepository.save(new PlaceEntity(place.getName(), place.getCity(), place.getStreet(), place.getBuildingNumber()));
        System.out.println("lol done");
    }

    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public void createEvent(@RequestBody EventDto event) {
        long locationId = event.getLocation();
        //TODO: null pointer exc
        PlaceEntity location = placeRepository.findOne(locationId);
        eventRepository.save(new EventEntity(event.getName(), event.getHashtag(), location, event.getCategories(), event.hasTickets()));
    }

    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void createPost(@RequestBody PostDto post) {
        long eventId = post.getEvent();
        //TODO: null pointer exc
        EventEntity event = eventRepository.findOne(eventId);
        postRepository.save(new PostEntity(event));
    }
    

    @RequestMapping(path = "/posts/{id}/comments", method = RequestMethod.POST)
    public void createCommentForPost(@PathVariable(value = "id") long id, @RequestBody CommentDto comment) {
        long postId = id;
        PostEntity post = postRepository.findOne(postId);
        commentRepository.save(new CommentEntity(comment.getContent(), post));
    }
}