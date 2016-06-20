package pl.edu.agh.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.tai.EventureApplication;
import pl.edu.agh.tai.persistence.EventRepository;
import pl.edu.agh.tai.persistence.PostRepository;
import pl.edu.agh.tai.persistence.UserRepository;
import pl.edu.agh.tai.persistence.dtos.PostDto;
import pl.edu.agh.tai.persistence.entitites.EventEntity;
import pl.edu.agh.tai.persistence.entitites.PostEntity;
import pl.edu.agh.tai.persistence.entitites.UserEntity;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="api/posts/{id}", method = RequestMethod.GET)
    public PostEntity showPost(@PathVariable(value="id") long id) {
        return postRepository.findOne(id);
    }

    @RequestMapping(path="api/posts", method = RequestMethod.GET)
    public Iterable<PostEntity> showPosts() throws IOException {
        return postRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path ="api/posts", method = RequestMethod.POST)
    public PostEntity addPost(@RequestBody PostDto post, Principal principal) {
        long eventId = post.getEvent();
        String username = EventureApplication.getPrincipalUsername(principal);
        EventEntity eventEntity = eventRepository.findOne(eventId);
        UserEntity userEntity = userRepository.findByUsername(username);
        return postRepository.save(new PostEntity(eventEntity, LocalDateTime.now(), userEntity));
    }

}
