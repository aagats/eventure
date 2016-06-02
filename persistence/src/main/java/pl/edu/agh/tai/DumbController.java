package pl.edu.agh.tai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    }

    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public void createEvent(@RequestBody EventDto event) {
        long locationId = event.getLocation();
        String dateTime = event.getDateTime();
        LocalDateTime localDateTime = parseStringToLocalDateTime(dateTime);
        //TODO: null pointer exc
        PlaceEntity location = placeRepository.findOne(locationId);
        eventRepository.save(new EventEntity(event.getName(), localDateTime, event.getHashtag(), location, event.getCategories(), event.hasTickets()));
    }

    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void createPost(@RequestBody PostDto post) {
        long eventId = post.getEvent();
        List<CommentEntity> comments = new ArrayList<>();
        //TODO: null pointer exc
        EventEntity event = eventRepository.findOne(eventId);
        postRepository.save(new PostEntity(event, LocalDateTime.now(), comments));
    }

    @RequestMapping(path = "/posts/{id}/comments", method = RequestMethod.POST)
    public void createCommentForPost(@PathVariable(value = "id") long id, @RequestBody CommentDto comment) {
        long postId = id;
        //TODO: null pointer exc
        PostEntity post = postRepository.findOne(postId);
        List<CommentEntity> comments = post.getComments();
        CommentEntity commentEntity = new CommentEntity(comment.getContent(), LocalDateTime.now(), post);
        comments.add(commentEntity);
        post.setComments(comments);
        commentRepository.save(commentEntity);
        postRepository.save(post);
    }

    private LocalDateTime parseStringToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

}