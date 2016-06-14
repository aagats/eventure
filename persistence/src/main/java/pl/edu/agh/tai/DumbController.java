package pl.edu.agh.tai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DumbController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/healthcheck")
    public String lol() {
        return "lol";
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

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public void createUser(@RequestBody UserDto user) {
        userRepository.save(new UserEntity(user.getUsername(), user.getRole()));
    }


}